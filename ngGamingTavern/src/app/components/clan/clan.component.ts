import { User } from "src/app/models/user/user";
import { AliasService } from "./../../services/alias/alias.service";
import { UserService } from "src/app/services/user/user.service";
import { AuthService } from "src/app/services/auth/auth.service";

import { Component, OnInit } from "@angular/core";
import { ClanService } from "src/app/services/clan/clan.service";
import { Clan } from "./../../models/clan/clan";
import { Alias } from "src/app/models/alias/alias";

@Component({
  selector: "app-clan",
  templateUrl: "./clan.component.html",
  styleUrls: ["./clan.component.scss"],
})
export class ClanComponent implements OnInit {
  ClanService: any;

  constructor(
    private clanSvc: ClanService,
    private auth: AuthService,
    private userSvc: UserService,
    private aliasSvc: AliasService
  ) {}

  selected: Clan | null = null;

  clans: Clan[] = [];

  leftColumnWidth = 4;

  topPadding = 3;

  rightColumnWidth = 12 - this.leftColumnWidth;

  leftHeader = "Clans";

  clanName = "";

  clanDescription = "";

  clanModel = "";

  clanImageUrl = "";

  clanCreated = "";

  clanUpdated : Clan | null = null;

  aliasId: number;

  clanData: [] = [];

  loggedInUser: User;

  newClan: Clan = new Clan();

  aliases: Alias[] = [];
  
  clanUpdate;

  showClan(id: number) {
    this.clanSvc.show(id).subscribe(
      (data) => {
        this.clanName = data.name;
        this.clanDescription = data.description;
        this.clanImageUrl = data.imageUrl;
        this.clanCreated = data.created;
        this.selected = data;
        this.clanUpdate = data.updated
      },
      (err) => console.error(err)
    );
  }

  onSubmit() {
    // this.product.push(this.newProduct)
    this.ClanService.create(this.newClan);
    this.newClan = new Clan();
    this.clans = this.ClanService.index();
  }

  ngOnInit(): void {
    // this.reload();
    this.getUserByUsername(this.auth.getCurrentUsername());
    this.loadData();
    this.getAliasesByUsername(this.auth.getCurrentUsername());
  }

  loadData = (): void => {
    this.clanSvc.getClanData().subscribe(
      (data) => {
        this.clanData = data;
      },
      (err) => console.error(err)
    );
  };

  createClan(clan: Clan) {
    console.log(this.auth.getCurrentUsername())
    console.error(this.loggedInUser);
    for (let alias of this.aliases) {
      console.log(this.aliasId + " : " + alias.id)
      if ((alias.id == this.aliasId)) {
        console.log("inside if")
        clan.creatorAlias = alias;
        clan.creatorAlias.user = this.loggedInUser;
      }
    }
    console.log(clan);
    this.clanSvc.create(clan).subscribe(
      (data) => {
        this.loadData();
        this.newClan = new Clan();
      },
      (err) => console.log("Observable got an error" + err)
    );
  }

  updateClan(clan: Clan, clanId : number){
    console.log(this.clanUpdated)
    this.clanSvc.update(clan, clanId).subscribe(
      (data) => {
        this.reload;
        this.clanUpdated = null;
        if(this.selected){
          this.selected = Object.assign({}, clan);
        }
      },
      (err) => console.log(err)
    );
  }

  setUpdateClan(){
    this.clanUpdated = Object.assign({}, this.selected)
  }

  getUserByUsername(username: string) {
    this.userSvc.getUserByUsername(username).subscribe(
      (data) => {
        this.loggedInUser = data;
      },
      (err) => {
        console.log(
          "ChatComponent getUserByUsername(): Observable got an error " + err
        );
      }
    );
  }

  getAliasesByUsername(username: string) {
    this.aliasSvc.showByUsername(username).subscribe(
      (data) => {
        this.aliases = data;
      },
      (err) => {
        console.log(
          "AliasComponent showByUsername().aliasService.showByUsername(): Observable got an error " +
            err
        );
      }
    );
  }



  reload = (): void => {
    this.clanSvc.index().subscribe(
      (data) => (this.clans = data),
      (err) => console.error(err)
    );
  }
}
