import { UserService } from "src/app/services/user/user.service";
import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { User } from "src/app/models/user/user";
import { GlobalComponent } from "src/app/global-component";
import { AuthService } from "src/app/services/auth/auth.service";
import {
  AbstractControl,
  FormControl,
  FormGroup,
  Validators,
} from "@angular/forms";
import { Game } from "src/app/models/game/game";

@Component({
  selector: "app-home",
  templateUrl: "./home.component.html",
  styleUrls: ["./home.component.scss"],
})
export class HomeComponent implements OnInit {
  validationForm: FormGroup;

  submitted = false;
  isUsernameTaken: boolean = false;
  user: User;
  isUserLoaded: boolean;
  newUser: User = new User();
  games: Game[] = [];
  showRegister: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private auth: AuthService
  ) {
    this.validationForm = new FormGroup({
      firstName: new FormControl(null, Validators.required),
      lastName: new FormControl(null, Validators.required),
      email: new FormControl(null, Validators.required),
    });
  }

  // global styling - make sure to add to top of page:
  // import { GlobalComponent } from "src/app/global-component";
  gridItemClass = GlobalComponent.gridItemClass;
  rippleColor = GlobalComponent.rippleColor;

  customRounding = GlobalComponent.customRounding;

  get firstName(): AbstractControl {
    return this.validationForm.get("firstName")!;
  }

  get lastName(): AbstractControl {
    return this.validationForm.get("lastName")!;
  }

  get email(): AbstractControl {
    return this.validationForm.get("email")!;
  }

  ngOnInit(): void {}

  revealRegister() {
    this.showRegister = true;
  }

  back() {
    this.showRegister = false;
  }

  register(user: User): void {
    console.log("Registering user:");
    console.log(user);
    this.auth.register(user).subscribe({
      next: (registeredUser) => {
        this.auth.login(user.username, user.password).subscribe({
          next: (loggedInUser) => {
            this.router.navigateByUrl("/users/" + user.username);
          },
          error: (problem) => {
            console.error(
              "RegisterComponent.register(): Error logging in user:"
            );
            console.error(problem);
          },
        });
      },
      error: (fail) => {
        console.error(
          "RegisterComponent.register(): Error registering account"
        );
        console.error("user account already taken");
        this.isUsernameTaken = true;
        console.error(fail);
      },
    });

    function checkUsername(username: string) {
      if (this.getUserByUsername(username)) {
        this.isUsernameTaken = true;
      }
    }

    function username(username: any, string: any) {
      throw new Error("Function not implemented.");
    }
  }
}
