import { User } from "src/app/models/user/user";
import { AliasService } from "./../../services/alias/alias.service";
import { UserService } from "src/app/services/user/user.service";
import { AuthService } from "src/app/services/auth/auth.service";
import { Component, OnInit } from "@angular/core";
import { ClanService } from "src/app/services/clan/clan.service";
import { Clan } from "./../../models/clan/clan";
import { Alias } from "src/app/models/alias/alias";
import { GlobalComponent } from "src/app/global-component";
import {
  AbstractControl,
  FormControl,
  FormGroup,
  Validators,
  ReactiveFormsModule
} from "@angular/forms";

@Component({
  selector: "app-clan",
  templateUrl: "./clan.component.html",
  styleUrls: ["./clan.component.scss"],
})
export class ClanComponent implements OnInit {
  validationForm: FormGroup;

  constructor(
    private clanSvc: ClanService,
    private auth: AuthService,
    private userSvc: UserService,
    private aliasSvc: AliasService
  ) {
    this.validationForm = new FormGroup({
      name: new FormControl(null, Validators.required),
      description: new FormControl(null),
      imageUrl: new FormControl(null)
    });
  }

  // global styling - make sure to add to top of page:
  // import { GlobalComponent } from "src/app/global-component";
  gridItemClass = GlobalComponent.gridItemClass;
  rippleColor = GlobalComponent.rippleColor;
  customRounding = GlobalComponent.customRounding;
  topPadding = GlobalComponent.topPadding;
  rounding = GlobalComponent.rounding;

  // booleans
  isLoggedInUserLoaded: boolean = false;
  isClansLoaded: boolean = false;
  isSelectedLoaded: boolean = false;
  isAliasesLoaded: boolean = false;
  isClansAliasesLoaded: boolean = false;
  isCreating: boolean = false;
  isUpdating: boolean = false;
  isLoading: boolean = false;
  invalidAliasId: boolean = false;

  // return true if no other modes are active
  // used to hide/show main page card(s) via *ngIf="emptyStatus()"
  emptyStatus = ():boolean => {
    return (!this.isLoading && !this.selected 
      && !this.isCreating && !this.isUpdating);
  }

  // no longer used
  // leftColumnWidth = 4;
  // rightColumnWidth = 12 - this.leftColumnWidth;
  // ClanService: any;

  // used in form
  creatorAlias: Alias;
  aliasId: number = -1; // -1 by default to allow ngmodel to properly bind to default (disabled)

  // used in pagination
  numRows: number = 10;

  // models
  clans: Clan[] = [];
  pagedClans: any[][] = new Array<Array<any>>();
  pagedClansIndex: number = 0;

  aliases: Alias[] = [];

  clanAliases: Alias[] = [];
  pagedClanAliases: any[][] = new Array<Array<any>>();
  pagedClanAliasesIndex: number = 0;

  selected: Clan | null = null;
  newClan: Clan = new Clan();
  loggedInUser: User;
  clanUpdated: Clan | null = null;

  ngOnInit(): void {
    this.getUserByUsername(this.auth.getCurrentUsername());
    this.getAliasesByUsername(this.auth.getCurrentUsername());
    this.getAllClans();
  }

  createPages = (arr: any[], pagedArr: any[][]): any => {
    let count:number = arr.length;
    let numPages = ((count - (count % this.numRows)) / this.numRows);
    if (count % this.numRows != 0) {
      numPages++;
    }
    // console.log('numPages', numPages);
    let index: number = 0;
    for (let i = 0; i < numPages; i++) {
      let page: any[] = [];
      // console.log('page (i)', i);
      for (let j = 0; j < this.numRows; j++) {
        if (arr[index]) {
          page.push(arr[index]);
        }
        index++;
      }
      pagedArr.push(page);
    }
  }

  tNext = (arr: any[][], index): number => {
    if (arr.length > index + 1) {
      return index + 1;
    } else {
      return index;
    }
  };

  tPrev = (arr: any[][], index): number => {
    if (index - 1 >= 0) {
      return index - 1;
    } else {
      return index;
    }
  };

  beginCreate = (): void => {
    this.isUpdating = false;
    if (this.aliasId === -1) {
      this.invalidAliasId = true;
    } else {
      this.invalidAliasId = false;
      this.doCreateClan();
    }
  }

  beginUpdate = (): void => {
    this.isCreating = false;
    if (this.aliasId === -1) {
      this.invalidAliasId = true;
    } else {
      this.invalidAliasId = false;
      this.doUpdateClan();
    }
  }

  resetAll = (): void => {
    this.resetClanUpdated();
    this.resetCreateForm();
    this.isCreating = false;
    this.isUpdating = false;
  }

  resetClanUpdated = (): void => {
    this.clanUpdated = new Clan();
    this.clanUpdated = Object.assign(this.clanUpdated, this.selected);
  }

  resetCreateForm = (): void => {
    this.newClan = new Clan();
  }

  isLoaded = (): boolean => {
    if (this.isLoggedInUserLoaded && this.isClansLoaded) {
      return true;
    } else {
      return false;
    }
  };

  // form validation
  get name(): AbstractControl {
    return this.validationForm.get("name")!;
  }

  get description(): AbstractControl {
    return this.validationForm.get("description")!;
  }

  get imageUrl(): AbstractControl {
    return this.validationForm.get("imageUrl")!;
  }

  selectClan(clan: Clan) {
    this.isLoading = true;
    this.getClanById(clan.id);
    this.getAliasesByClanId(clan.id);
  }

  getClanById(id: number) {
    this.clanSvc.show(id).subscribe(
      (data) => {
        this.selected = data;
        this.isSelectedLoaded = true;
        this.isLoading = false;
      },
      (err) => console.error(
        "ClanComponent getClanById(): Observable got an error " + err)
    );
  }

  getAllClans = (): void => {
    this.clanSvc.getClanData().subscribe(
      (data) => {
        this.clans = data;
        this.pagedClans = new Array<Array<any>>();
        this.createPages(this.clans, this.pagedClans);
        // console.log(this.pagedClans);
        this.isClansLoaded = true;
        this.isLoading = false;
      },
      (err) => console.error(
        "ClanComponent getAllClans(): Observable got an error " + err)
    );
  };

  createClan(clan: Clan) {
    this.isLoading = true;
    // find creator alias
    for (let alias of this.aliases) {
      // console.log(this.aliasId + " : " + alias.id);
      if (alias.id == this.aliasId) {
        // set creatorAlias to clan
        clan.creatorAlias = alias;
        // set loggedInUser to clan's creatorAlias
        clan.creatorAlias.user = this.loggedInUser;
      }
    }
    this.clanSvc.create(clan).subscribe(
      (data) => {
        
        // reset newClan
        this.newClan = new Clan();

        // reload selected from db to verify
        this.getClanById(data.id);

        // reload clans
        this.getAllClans();
        
        this.getAliasesByClanId(data.id);
        this.isLoading = false;
      },
      (err) => console.error(
        "ClanComponent createClan(): Observable got an error" + err)
    );
  }

  doCreateClan() {
    // set create time
    let now = new Date(Date.now()).toISOString();
    this.newClan.created = now;

    // hide create card
    this.isCreating = false;
    // update in DB
    this.createClan(this.newClan);
  }

  updateClan(clan: Clan) {
    this.isLoading = true;
    this.clanSvc.update(clan, clan.id).subscribe(
      (data) => {
        // reload clans
        this.getAllClans();
        // reset clanUpdated
        this.clanUpdated = new Clan();
        // reload selected from db to verify
        this.getClanById(data.id);
        this.isLoading = false;
      },
      (err) => console.error(
        "ClanComponent updateClan(): Observable got an error" + err)
    );
  }

  doUpdateClan() {
    // find and set alias
    for (let alias of this.aliases) {
      if (alias.id === this.aliasId) {
        this.clanUpdated.creatorAlias = alias;
      }
    }

    // set update time
    let now = new Date(Date.now()).toISOString();
    this.clanUpdated.updated = now;

    console.log(this.clanUpdated);

    // hide update card
    this.isUpdating = false;
    // update in DB
    this.updateClan(this.clanUpdated);
  }

  deleteClanById(id: number) {
    this.isLoading = true;
    this.clanSvc.delete(id).subscribe(
      (data) => {
        // reload clans
        this.getAllClans();
        this.selected = null;
        this.isSelectedLoaded = false;
        this.isUpdating = false;
        this.isCreating = false;
        this.isLoading = false;
      },
      (err) => console.error(
        "ClanComponent deleteClanById(): Observable got an error" + err)
    );
  }

  getUserByUsername(username: string) {
    this.isLoading = true;
    this.userSvc.getUserByUsername(username).subscribe(
      (data) => {
        this.loggedInUser = data;
        this.isLoggedInUserLoaded = true;
        this.isLoading = false;
      },
      (err) => {
        console.error(
          "ChatComponent getUserByUsername(): Observable got an error " + err
        );
      }
    );
  }

  getAliasesByUsername(username: string) {
    this.isLoading = true;
    this.aliasSvc.showByUsername(username).subscribe(
      (data) => {
        this.aliases = data;
        this.isAliasesLoaded = true;
        this.isLoading = false;
      },
      (err) => {
        console.error(
          "ChatComponent getAliasesByUsername(): Observable got an error " +
            err
        );
      }
    );
  }
  
  getAliasesByClanId(id: number) {
    this.isLoading = true;
    this.aliasSvc.showByClanId(id).subscribe(
      (data) => {
        this.clanAliases = data;
        this.isClansAliasesLoaded = true;
        this.isLoading = false;
        this.createPages(this.clanAliases, this.pagedClanAliases);
      },
      (err) => {
        console.error(
          "ChatComponent getAliasesByUsername(): Observable got an error " +
            err
        );
      }
    );
  }

  
}
