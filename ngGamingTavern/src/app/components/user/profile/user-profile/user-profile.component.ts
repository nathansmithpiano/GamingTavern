import { DatePipe } from '@angular/common';
import { AuthService } from './../../../../services/auth/auth.service';
import { UserService } from './../../../../services/user/user.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user/user';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss'],
})
export class UserProfileComponent implements OnInit {
  constructor(
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router,
    private auth: AuthService,
    private datePipe: DatePipe
  ) {}

  title = 'User Profile';
  isLoading: boolean = true;
  currentUserId: number;
  user: User = new User();
  selectedUser: User | null;
  loginUser: User = new User();

  // used in loadUserInfo to display on page after user is loaded
  userHeader: string = '';
  userDescription: string = '';
  userCreated: string = '';
  userUpdated: string = '';

  // html settings
  topPadding = 3;

  // left card
  leftCardWidth = 3;
  profileImageWidth = '175px';
  headerMarginTop = 2;
  descriptionMarginTop = 0;
  leftIconMarginEnd = 3;
  textClass = 'text-muted';
  leftSummaryClass = 'text-center text-muted';
  leftSummaryCol1Width = 5;
  leftSummaryCol2Width = 7;
  leftSummaryRowMargin = 2;
  leftSummaryRow1Col1Header = 'Friends';
  leftSummaryRow1Col1Value = 134;
  leftSummaryRow1Col2Header = 'Endorsements';
  leftSummaryRow1Col2Value = 3471;
  leftSummaryRow2Col1Header = 'Aliases';
  leftSummaryRow2Col1Value = 47;
  leftSummaryRow2Col2Header = 'Games';
  leftSummaryRow2Col2Value = 21;
  leftSummaryRow3Col1Header = 'Clans (Member)';
  leftSummaryRow3Col1Value = 47;
  leftSummaryRow3Col2Header = 'Clans (Owner/Admin)';
  leftSummaryRow3Col2Value = 21;
  friendButtonLabel = 'Add Friend';
  friendButtonType = 'success';
  chatButtonLabel = 'Chat';
  chatButtonType = 'info';
  endorsementButtonLabel = 'Give Endorsement';
  endorsementButtonType = 'primary';
  blockButtonLabel = 'Block';
  blockButtonType = 'danger';

  // right card
  rightColumnWidth = 12 - this.leftCardWidth;
  rightColumn1Card1Width = 5;
  rightColumn1Card2Width = 12 - this.rightColumn1Card1Width;

  loadUserInfo = (): void => {
    this.userHeader = this.getFullName();
    this.userDescription = this.user.description;
    this.userCreated = this.datePipe.transform(this.user.created, 'short');
    this.userUpdated = this.datePipe.transform(this.user.updated, 'short');
  };

  ngOnInit(): void {
    this.currentUserId = parseInt('' + this.auth.getCurrentUserId());
    console.log(this.currentUserId);
    this.show(this.currentUserId);

    if (!this.selectedUser && this.route.snapshot.paramMap.get('id')) {
      let id = this.route.snapshot.paramMap.get('id');
      if (id) {
        this.show(parseInt(id));
      }
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
    let output: string = '';
    if (this.user.firstName) {
      output += this.user.firstName;
      if (this.user.middleName) {
        output += ' ';
      }
    }
    if (this.user.middleName) {
      output += this.user.middleName;
      if (this.user.lastName) {
        output += ' ';
      }
    }
    if (this.user.lastName) {
      output += ' ' + this.user.lastName;
    }
    return output;
  };

  show(id: number) {
    console.log('show() about to look for id=' + id);
    this.userService.show(id).subscribe(
      (data) => {
        this.user = data;
        console.log('show() found data, data=' + data);
        this.loadUserInfo();
        this.isLoading = false;
        if (!this.user) {
          this.router.navigateByUrl('/notFound');
        }
      },
      (err) => {
        if (!this.user) {
          this.router.navigateByUrl('/notFound');
        }
        console.log('TodoListCompoment show(): Observable got an error ' + err);
      }
    );
  }
}
