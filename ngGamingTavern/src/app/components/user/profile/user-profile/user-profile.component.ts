import { DatePipe } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { Alias } from "src/app/models/alias/alias";
import { Equipment } from "src/app/models/equipment/equipment";
import { Game } from "src/app/models/game/game";
import { User } from "src/app/models/user/user";
import { Meetup } from "./../../../../models/meetup/meetup";
import { AliasService } from "./../../../../services/alias/alias.service";
import { AuthService } from "./../../../../services/auth/auth.service";
import { UserService } from "./../../../../services/user/user.service";
import {
  AbstractControl,
  FormControl,
  FormGroup,
  Validators,
  ReactiveFormsModule,
} from "@angular/forms";

@Component({
  selector: "app-user-profile",
  templateUrl: "./user-profile.component.html",
  styleUrls: ["./user-profile.component.scss"],
})
export class UserProfileComponent implements OnInit {
  validationForm: FormGroup;

  constructor(
    private userService: UserService,
    private aliasService: AliasService,
    private route: ActivatedRoute,
    private router: Router,
    private auth: AuthService,
    private datePipe: DatePipe
  ) {
    this.validationForm = new FormGroup({
      email: new FormControl(null, Validators.required),
      firstName: new FormControl(null),
      middleName: new FormControl(null),
      lastName: new FormControl(null),
      description: new FormControl(null),
      imageUrl: new FormControl(null),
    });
  }

  title = "User Profile";

  // models
  user: User = new User();
  allUsernames: String[] = [];
  pagedAllUsernames:  any[][] = new Array<Array<any>>();
  pagedAllUsernamesIndex: number = 0;

  equipments: Equipment[] = [];
  pagedEquipments: any[][] = new Array<Array<any>>();
  pagedEquipmentsIndex: number = 0;

  aliases: Alias[] = [];
  pagedAliases: any[][] = new Array<Array<any>>();
  pagedAliasesIndex: number = 0;

  friends: User[] = [];
  pagedFriends: any[][] = new Array<Array<any>>();
  pagedFriendsIndex: number = 0;

  games: Game[] = [];
  pagedGames: any[][] = new Array<Array<any>>();
  pagedGamesIndex: number = 0;

  locations: Location[] = [];
  pagedLocations: any[][] = new Array<Array<any>>();
  pagedLocationsIndex: number = 0;

  meetups: Meetup[] = [];
  pagedMeetups: any[][] = new Array<Array<any>>();
  pagedMeetupsIndex: number = 0;

  blockedUsers: User[] = [];
  pagedBlockedUsers: any[][] = new Array<Array<any>>();
  pagedBlockedUsersIndex: number = 0;

  updateUser: User = new User();

  // used in pagination
  numRows: number = 10;

  // loaders
  isLoading: boolean = false;
  isUserLoaded: boolean = false;
  isAllUsernamesLoaded: boolean = false;
  isEquipmentsLoaded: boolean = false;
  isAliasesLoaded: boolean = false;
  isFriendsLoaded: boolean = false;
  isBlockedUsersLoaded: boolean = false;
  isGamesLoaded: boolean = false;
  isLocationsLoaded: boolean = false;
  isMeetupsLoaded: boolean = false;

  // modes
  isUpdating: boolean = false;

  isLoaded = (): boolean => {
    if (
      this.isUserLoaded &&
      this.isAllUsernamesLoaded &&
      this.isEquipmentsLoaded &&
      this.isAliasesLoaded &&
      this.isFriendsLoaded &&
      this.isBlockedUsersLoaded &&
      this.isGamesLoaded &&
      this.isLocationsLoaded &&
      this.isMeetupsLoaded
    ) {
      return true;
    } else {
      return false;
    }
  };

  // html settings
  topPadding = 3;
  rounding: number = 5;
  gridItemClass: string = "shadow-4-strong rounded-5";
  textClass = "text-muted";

  // left card
  leftCardWidth = 3;
  profileImageWidth = "175px";
  headerMarginTop = 2;
  descriptionMarginTop = 0;
  leftIconMarginEnd = 3;
  leftSummaryClass = "text-center text-muted";
  leftSummaryCol1Width = 5;
  leftSummaryCol2Width = 7;
  leftSummaryRowMargin = 2;
  leftSummaryRow2Col2Header = "Games";
  leftSummaryRow2Col2Value = 9999;
  leftSummaryRow3Col1Header = "Clans (Member)";
  leftSummaryRow3Col1Value = 47;
  leftSummaryRow3Col2Header = "Clans (Owner/Admin)";
  leftSummaryRow3Col2Value = 21;
  updateButtonType = "success";
  updateButtonLabel = "Update";
  friendButtonLabel = "Add Friend";
  friendButtonType = "success";
  chatButtonLabel = "Chat";
  chatButtonType = "info";
  endorsementButtonLabel = "Endorse";
  endorsementButtonType = "primary";
  blockButtonLabel = "Block";
  blockButtonType = "danger";

  // right cards
  rightColumnWidth = 12 - this.leftCardWidth;
  rightRow1Card1Width = 3;
  rightRow1Card2Width = 3;
  rightRow1Card3Width = 3;
  rightRow1Card4Width = 3;
  rightRow2Card1Width = 6;
  rightRow2Card2Width = 6;

  ngOnInit(): void {
    // if username provided as param
    if (this.route.snapshot.paramMap.get("username")) {
      let username = this.route.snapshot.paramMap.get("username");
      if (username) {
        this.getUserByUsername(username);
      }
    } else {
      // no param, show logged in user
      this.getUserByUsername(this.auth.getCurrentUsername());
    }
  }

  createPages = (arr: any[], pagedArr: any[][]): any => {
    let count: number = arr.length;
    let numPages = (count - (count % this.numRows)) / this.numRows;
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
  };

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

  isCurrentUser = (): boolean => {
    if (this.user.id === parseInt(this.auth.getCurrentUserId())) {
      return true;
    } else {
      return false;
    }
  };

  getFullName = (): string => {
    let output: string = "";
    if (this.user.firstName) {
      output += this.user.firstName;
      if (this.user.middleName) {
        output += " ";
      }
    }
    if (this.user.middleName) {
      output += this.user.middleName;
      if (this.user.lastName) {
        output += " ";
      }
    }
    if (this.user.lastName) {
      output += " " + this.user.lastName;
    }
    return output;
  };

  // form validation
  get email(): AbstractControl {
    return this.validationForm.get("email")!;
  }

  get firstName(): AbstractControl {
    return this.validationForm.get("firstName")!;
  }

  get middleName(): AbstractControl {
    return this.validationForm.get("firstName")!;
  }

  get lastName(): AbstractControl {
    return this.validationForm.get("lastName")!;
  }

  get description(): AbstractControl {
    return this.validationForm.get("description")!;
  }

  get imageUrl(): AbstractControl {
    return this.validationForm.get("imageUrl")!;
  }

  reload = () => {
    this.getAllUsernames();
    this.getEquipmentByUsername(this.user.username);
    this.getAliasesByUsername(this.user.username);
    this.getFriendsByUsername(this.user.username);
    this.getBlockedUsersByUsername(this.user.username);
    this.getGamesByUsername(this.user.username);
    this.getLocationsByUsername(this.user.username);
    this.getMeetupsByUsername(this.user.username);
  };

  resetUpdateUser = (): void => {
    this.updateUser = Object.assign(this.updateUser, this.user);
  }

  beginUpdate = (): void => {
    if (this.updateUser.email) {
      this.isUpdating = true;
      console.log(this.updateUser);

      this.getUpdateUser(this.updateUser);
    }
  }

  getAllUsernames() {
    this.userService.getAllUsernames().subscribe(
    (data) => {
      this.allUsernames = data;
      this.pagedAllUsernames = new Array<Array<any>>();
      this.createPages(this.allUsernames, this.pagedAllUsernames);

      this.isAllUsernamesLoaded = true;
    },
    (err) => {
      console.error(
        "UserProfileComponent getAllUsernames(): Observable got an error " + err
      );
      this.isAllUsernamesLoaded = true;
    }
  );
}

  getUserByUsername(username: string) {
    this.userService.getUserByUsername(username).subscribe(
      (data) => {
        this.user = data;
        this.isUserLoaded = true;
        this.reload();
        if (!this.user) {
          this.router.navigateByUrl("/notFound");
        }
      },
      (err) => {
        if (!this.user) {
          this.router.navigateByUrl("/notFound");
        }
        console.error(
          "UserProfileComponent show(): Observable got an error " + err
        );
        this.isUserLoaded = true;
      }
    );
  }

  getEquipmentByUsername(username: string) {
    this.userService.getEquipmentsByUsername(this.user.username).subscribe(
      (data) => {
        this.equipments = data;
        this.pagedEquipments = new Array<Array<any>>();
        this.createPages(this.equipments, this.pagedEquipments);

        this.isEquipmentsLoaded = true;
      },
      (err) => {
        console.log(
          "UserProfileComponent reload().aliasService.showByUsername(): Observable got an error " +
            err
        );
        this.isEquipmentsLoaded = true;
      }
    );
  }

  getAliasesByUsername(username: string) {
    this.aliasService.showByUsername(this.user.username).subscribe(
      (data) => {
        this.aliases = data;
        this.pagedAliases = new Array<Array<any>>();
        this.createPages(this.aliases, this.pagedAliases);

        this.isAliasesLoaded = true;
      },
      (err) => {
        console.log(
          "UserProfileComponent reload().aliasService.showByUsername(): Observable got an error " +
            err
        );
        this.isAliasesLoaded = true;
      }
    );
  }

  getFriendsByUsername(username: string) {
    this.userService.getFriendsByUsername(username).subscribe(
      (data) => {
        this.friends = data;
        this.pagedFriends = new Array<Array<any>>();
        this.createPages(this.friends, this.pagedFriends);

        this.isFriendsLoaded = true;
      },
      (err) => {
        console.log(
          "UserProfileComponent getFriendsByUsername(): Observable got an error " +
            err
        );
        this.isFriendsLoaded = true;
      }
    );
  }

  getBlockedUsersByUsername(username: string) {
    this.userService.getBlockedUsersByUsername(username).subscribe(
      (data) => {
        this.blockedUsers = data;
        this.pagedBlockedUsers = new Array<Array<any>>();
        this.createPages(this.blockedUsers, this.pagedBlockedUsers);

        this.isBlockedUsersLoaded = true;
      },
      (err) => {
        console.log(
          "UserProfileComponent getBlockedUsersByUsername(): Observable got an error " +
            err
        );
        this.isBlockedUsersLoaded = true;
      }
    );
  }

  getGamesByUsername(username: string) {
    this.userService.getGamesByUsername(username).subscribe(
      (data) => {
        this.games = data;
        this.pagedGames = new Array<Array<any>>();
        this.createPages(this.games, this.pagedGames);

        this.isGamesLoaded = true;
      },
      (err) => {
        console.log(
          "UserProfileComponent getGamesByUsername(): Observable got an error " +
            err
        );
        this.isGamesLoaded = true;
      }
    );
  }

  getLocationsByUsername(username: string) {
    this.userService.getLocationsByUsername(username).subscribe(
      (data) => {
        this.locations = data;
        this.pagedLocations = new Array<Array<any>>();
        this.createPages(this.games, this.pagedLocations);

        this.isLocationsLoaded = true;
      },
      (err) => {
        console.log(
          "UserProfileComponent getGamesByUsername(): Observable got an error " +
            err
        );
        this.isLocationsLoaded = true;
      }
    );
  }

  getMeetupsByUsername(username: string) {
    this.userService.getMeetupsByUsername(username).subscribe(
      (data) => {
        this.meetups = data;
        this.pagedMeetups = new Array<Array<any>>();
        this.createPages(this.meetups, this.pagedMeetups);

        this.isMeetupsLoaded = true;
      },
      (err) => {
        console.log(
          "UserProfileComponent getMeetupsByUsername(): Observable got an error " +
            err
        );
        this.isMeetupsLoaded = true;
      }
    );
  }

  getUpdateUser(user: User) {
    // user.id = this.user.id;
    this.userService.updateUser(user).subscribe(
      (data) => {
        console.log("user updated successfully");
        this.user = data;
        this.isUserLoaded = true;
      },
      (err) => {
        console.log(
          "UserProfileComponent getUpdateUser(): Observable got an error " + err
        );
      }
    );
  }
}
