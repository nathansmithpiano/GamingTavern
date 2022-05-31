import { AuthService } from "src/app/services/auth/auth.service";
import { User } from "src/app/models/user/user";
import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";

@Component({
  selector: "login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.scss"],
})
export class LoginComponent implements OnInit {
  constructor(private router: Router, private auth: AuthService) {}

  loginUser: User = new User();

  ngOnInit(): void {
    let testUser: User = new User();
    testUser.username = 'admin';
    testUser.password = 'admin';
    this.login(testUser);
  }

  login = (user: User): void => {
    console.log("LOGIN", user.username, user.password);
    this.auth.login(user.username, user.password).subscribe({
      next: (loggedInUser) => {
        this.router.navigateByUrl("/user");
      },
      error: (error) => {
        console.log("login error: " + error);
      },
    });
  };
}
