import { UserService } from 'src/app/services/user/user.service';
import { NavComponent } from './../../../page/nav/nav.component';
import { Router } from "@angular/router";
import { Component, OnInit } from "@angular/core";
import { MdbModalRef } from "mdb-angular-ui-kit/modal";
import { User } from "src/app/models/user/user";
import { AuthService } from "src/app/services/auth/auth.service";

@Component({
  selector: "app-login-modal",
  templateUrl: "./login-modal.component.html",
  styleUrls: ["./login-modal.component.scss"],
})
export class LoginModalComponent implements OnInit {
  constructor(
    public loginModalRef: MdbModalRef<LoginModalComponent>,
    private router: Router,
    private auth: AuthService,
    private userService: UserService
  ) {}

  ngOnInit(): void {}

  private loginUser: User = new User();

  username: string = "";
  password: string = "";
  statusMessage: string = "";

  tryLogin = (): void => {
    console.log(this.username, this.password);
    
  };

  login = (): void => {
    this.tryLogin();
    this.auth.login(this.username, this.password).subscribe({
      next: (loggedInUser) => {
        this.loginModalRef.close()
        this.router.navigateByUrl("/user");
      },
      error: (error) => {
        console.log("login error: " + error);
      },
    });
  };

  checkForm = (): void => {
    "use strict";

    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    const forms = document.querySelectorAll(".needs-validation");

    // Loop over them and prevent submission
    Array.prototype.slice.call(forms).forEach((form) => {
      form.addEventListener(
        "click",
        (event) => {
          if (!form.checkValidity()) {
            event.preventDefault();
            event.stopPropagation();
          }
          form.classList.add("was-validated");
        },
        false
      );
    });

    if (this.username && this.password) {
      console.log("step 1");
      this.login();
    }
  };
}
