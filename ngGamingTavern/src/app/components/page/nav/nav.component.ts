import { AuthService } from "src/app/services/auth/auth.service";
import { Component, OnInit } from "@angular/core";
import { LoginModalComponent } from "../../auth/login/login-modal/login-modal.component";
import { MdbModalRef, MdbModalService } from "mdb-angular-ui-kit/modal";

@Component({
  selector: "nav-main",
  templateUrl: "./nav.component.html",
  styleUrls: ["./nav.component.scss"],
})
export class NavComponent implements OnInit {
  loginModalRef: MdbModalRef<LoginModalComponent> | null = null;

  constructor(
    private auth: AuthService,
    private loginModalService: MdbModalService
  ) {}

  ngOnInit(): void {
    // if user is logged in, currentUserId,
    // currentUsername, and credentials are in local storage
    console.log(this.auth.getCredentials);
  }

  openLoginModal(): void {
    this.loginModalRef = this.loginModalService.open(LoginModalComponent);
  }

  sayHello(): void {
    console.log('hello');
  }

  isLoggedIn = (): boolean => {
    if (this.auth.getCredentials()) {
      return true;
    } else {
      return false;
    }
  };

  friendsBadgeCount: number = 3;
  chatBadgeCount: number = 132;
  endorsementBadgeCount: number = 11;
  rippleColor = "hsl(0, 0%, 75%)";

  // labels
  registerLabel: string = "Register";
  loginLabel: string = "Login";
  profileLabel: string = "My Profile";
  logoutLabel: string = "Logout";

  // classes for various html tags
  searchFormClass: string = "input-group w-auto my-auto d-none d-sm-flex";
  searchInputClass: string = "bg-glass custSearchAdj form-control";

  iconSpanClass: string = "";

  centerItemsAClass: string = "nav-link custCentAdj";
  centerItemsBadgeSpanClass: string =
    "badge custBadgeAdj rounded-pill badge-notification bg-danger";

  rightItemsAClass: string = "nav-link d-sm-flex align-items-sm-center";
  rightItemStrongClass: string = "d-none d-sm-inline me-0";

  liClass: string = "nav-item me-1 me-lg-1";
}
