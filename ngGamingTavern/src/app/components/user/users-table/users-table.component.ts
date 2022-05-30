import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { User } from "src/app/models/user/user";
import { UserService } from "src/app/services/user/user.service";

@Component({
  selector: "app-users-table",
  templateUrl: "./users-table.component.html",
  styleUrls: ["./users-table.component.scss"],
})
export class UsersTableComponent implements OnInit {
  constructor(
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  title = "Users Table";
  isLoading: boolean = false;
  users: User[] = [];

  ngOnInit(): void {
    console.log('in users table');
    this.reload();
  }

  // attempt to obtain all from API
  reload = (): void => {
    this.isLoading = true;
    this.userService.index().subscribe(
      (data) => {
        this.users = data;
        console.log(this.users.length);
        this.isLoading = false;
      },
      (err) => {
        console.error('UsersTableComponent index() says: ' + err);
      }
    );
  };
}
