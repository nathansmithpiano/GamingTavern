import { Meetup } from "./../../../../models/meetup/meetup";
import { AliasService } from "./../../../../services/alias/alias.service";
import { DatePipe } from "@angular/common";
import { AuthService } from "./../../../../services/auth/auth.service";
import { UserService } from "./../../../../services/user/user.service";
import { Component, OnInit } from "@angular/core";
import { User } from "src/app/models/user/user";
import { ActivatedRoute, Router } from "@angular/router";
import { Alias } from "src/app/models/alias/alias";
import { Game } from "src/app/models/game/game";

@Component({
  selector: "app-user-profile",
  templateUrl: "./user-profile.component.html",
  styleUrls: ["./user-profile.component.scss"],
})
export class UserProfileComponent implements OnInit {
  constructor(
    private userService: UserService,
    private aliasService: AliasService,
    private route: ActivatedRoute,
    private router: Router,
    private auth: AuthService,
    private datePipe: DatePipe
  ) {}

  title = "User Profile";
  isLoading: boolean = true;
  user: User = new User();
  aliases: Alias[] = [];
  friends: User[] = [];
  friendsDisp: any[][] = new Array<Array<any>>();
  friendsPage: number = 0;
  games: Game[] = [];
  locations: Location[] = [];
  meetups: Meetup[] = [];
  blockedUsers: User[] = [];

  // html settings
  topPadding = 3;

  // left card
  leftCardWidth = 3;
  profileImageWidth = "175px";
  headerMarginTop = 2;
  descriptionMarginTop = 0;
  leftIconMarginEnd = 3;
  textClass = "text-muted";
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
  friendButtonLabel = "Add Friend";
  friendButtonType = "success";
  chatButtonLabel = "Chat";
  chatButtonType = "info";
  endorsementButtonLabel = "Give Endorsement";
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

  tNext = (arr: any[][], index): number => {
    if (arr.length > 5 && index + 2 < arr.length) {
      return index + 1;
    } else {
      return index;
    }
  };

  tPrev = (arr: any[][], index): number => {
    if (arr.length > 5 && index > 0) {
      return index - 1;
    } else {
      return index;
    }
  };

  tabler = (arr: any[], obj1: any[][], num): void => {
    let pages = (arr.length - (arr.length % num)) / num + 1;
    console.log("pages", pages);
    let start = 0;
    for (let i = 0; i < pages; i++) {
      let obj2: any[] = new Array<any>();
      for (let j = 0; j < num; j++) {
        if (arr[start]) {
          obj2.push(arr[start]);
        }
        start++;
      }
      obj1.push(obj2);
    }
  };

  ngOnInit(): void {
    // this.currentUsername = this.auth.getCurrentUsername();
    // console.log(this.currentUsername);
    // this.getUserByUsername(this.currentUsername);

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

  reload = () => {
    // this.aliasService.showByUsername(this.currentUsername).subscribe(
    this.aliasService.showByUsername(this.user.username).subscribe(
      (data) => {
        this.aliases = data;
      },
      (err) => {
        console.log(
          "UserProfileComponent reload().aliasService.showByUsername(): Observable got an error " +
            err
        );
      }
    );
    this.getFriendsByUsername(this.user.username);
    this.getBlockedUsersByUsername(this.user.username);
    this.getGamesByUsername(this.user.username);
    this.getLocationsByUsername(this.user.username);
    this.getMeetupsByUsername(this.user.username);
  };

  getUserByUsername(username: string) {
    // console.log('show() about to look for id=' + id);
    this.userService.getUserByUsername(username).subscribe(
      (data) => {
        this.user = data;
        // console.log('show() found data, data=' + data);
        this.reload();
        this.isLoading = false;
        if (!this.user) {
          this.router.navigateByUrl("/notFound");
        }
      },
      (err) => {
        if (!this.user) {
          this.router.navigateByUrl("/notFound");
        }
        console.log(
          "UserProfileComponent show(): Observable got an error " + err
        );
      }
    );
  }

  getFriendsByUsername(username: string) {
    this.userService.getFriendsByUsername(username).subscribe(
      (data) => {
        this.friends = data;
        this.isLoading = false;
        this.tabler(this.friends, this.friendsDisp, 5);
      },
      (err) => {
        console.log(
          "UserProfileComponent getFriendsByUsername(): Observable got an error " +
            err
        );
      }
    );
  }

  getBlockedUsersByUsername(username: string) {
    this.userService.getBlockedUsersByUsername(username).subscribe(
      (data) => {
        this.blockedUsers = data;
        this.isLoading = false;
      },
      (err) => {
        console.log(
          "UserProfileComponent getBlockedUsersByUsername(): Observable got an error " +
            err
        );
      }
    );
  }

  getGamesByUsername(username: string) {
    this.userService.getGamesByUsername(username).subscribe(
      (data) => {
        this.games = data;
        this.isLoading = false;
      },
      (err) => {
        console.log(
          "UserProfileComponent getGamesByUsername(): Observable got an error " +
            err
        );
      }
    );
  }

  getLocationsByUsername(username: string) {
    this.userService.getLocationsByUsername(username).subscribe(
      (data) => {
        this.locations = data;
        this.isLoading = false;
      },
      (err) => {
        console.log(
          "UserProfileComponent getGamesByUsername(): Observable got an error " +
            err
        );
      }
    );
  }

  getMeetupsByUsername(username: string) {
    this.userService.getMeetupsByUsername(username).subscribe(
      (data) => {
        this.meetups = data;
        this.isLoading = false;
      },
      (err) => {
        console.log(
          "UserProfileComponent getMeetupsByUsername(): Observable got an error " +
            err
        );
      }
    );
  }
}
