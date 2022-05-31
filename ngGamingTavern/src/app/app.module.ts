import { RegisterComponent } from "./components/auth/register/register.component";
import { UserService } from "./services/user/user.service";
import { HttpClientModule } from "@angular/common/http";
import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { AppRoutingModule } from "src/app/app-routing-module";
import { AppComponent } from "./app.component";

// MDB Modules

import { MdbAccordionModule } from "mdb-angular-ui-kit/accordion";
import { MdbCarouselModule } from "mdb-angular-ui-kit/carousel";
import { MdbCheckboxModule } from "mdb-angular-ui-kit/checkbox";
import { MdbCollapseModule } from "mdb-angular-ui-kit/collapse";
import { MdbDropdownModule } from "mdb-angular-ui-kit/dropdown";
import { MdbFormsModule } from "mdb-angular-ui-kit/forms";
import { MdbModalModule } from "mdb-angular-ui-kit/modal";
import { MdbPopoverModule } from "mdb-angular-ui-kit/popover";
import { MdbRadioModule } from "mdb-angular-ui-kit/radio";
import { MdbRangeModule } from "mdb-angular-ui-kit/range";
import { MdbRippleModule } from "mdb-angular-ui-kit/ripple";
import { MdbScrollspyModule } from "mdb-angular-ui-kit/scrollspy";
import { MdbTabsModule } from "mdb-angular-ui-kit/tabs";
import { MdbTooltipModule } from "mdb-angular-ui-kit/tooltip";
import { MdbValidationModule } from "mdb-angular-ui-kit/validation";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { NavComponent } from "./components/page/nav/nav.component";
import { LoginComponent } from "./components/auth/login/login.component";
import { UserProfileComponent } from "./components/user/profile/user-profile/user-profile.component";
import { DatePipe } from "@angular/common";

import { UsersTableComponent } from "./components/user/users-table/users-table.component";
import { HomeComponent } from "./components/home/home.component";
import { LogoutComponent } from "./components/auth/logout/logout.component";
import { LoginModalComponent } from "./components/auth/login/login-modal/login-modal.component";
import { FormsModule } from "@angular/forms";
import { SplashComponent } from "./components/splash/splash.component";
import { ClanComponent } from "./components/clan/clan.component";
import { EquipmentComponent } from "./components/equipment/equipment.component";
import { ServerComponent } from "./components/server/server.component";
import { MeetupComponent } from "./components/meetup/meetup.component";
import { GameComponent } from "./components/game/game.component";

@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    LoginComponent,
    UserProfileComponent,
    RegisterComponent,
    UsersTableComponent,
    HomeComponent,
    LogoutComponent,
    LoginModalComponent,
    SplashComponent,
    ClanComponent,
    EquipmentComponent,
    ServerComponent,
    MeetupComponent,
    GameComponent,
  ],

  imports: [
    BrowserModule,
    FormsModule,
    BrowserAnimationsModule,
    HttpClientModule,
    AppRoutingModule,
    MdbAccordionModule,
    MdbCarouselModule,
    MdbCheckboxModule,
    MdbCollapseModule,
    MdbDropdownModule,
    MdbFormsModule,
    MdbModalModule,
    MdbPopoverModule,
    MdbRadioModule,
    MdbRangeModule,
    MdbRippleModule,
    MdbScrollspyModule,
    MdbTabsModule,
    MdbTooltipModule,
    MdbValidationModule,
  ],
  providers: [UserService, DatePipe],
  bootstrap: [AppComponent],
})
export class AppModule {}
