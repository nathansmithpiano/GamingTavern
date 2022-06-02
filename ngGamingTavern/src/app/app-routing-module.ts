import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { HomeComponent } from './components/home/home.component';
import { SplashComponent } from "./components/splash/splash.component";
import { LoginComponent } from "./components/auth/login/login.component";
import { LogoutComponent } from "./components/auth/logout/logout.component";
import { RegisterComponent } from "./components/auth/register/register.component";
import { UserProfileComponent } from "./components/user/profile/user-profile/user-profile.component";
import { ChatComponent } from "./components/chat/chat/chat.component";
import { ClanComponent } from "./components/clan/clan.component";
import { EquipmentComponent } from "./components/equipment/equipment.component";
import { ServerComponent } from "./components/server/server.component";
import { MeetupComponent } from "./components/meetup/meetup.component";
import { GameComponent } from "./components/game/game.component";

const routes: Routes = [
  { path: "", pathMatch: "full", component: HomeComponent },
  { path: "home", pathMatch: "full", component: HomeComponent },
  { path: "splash", pathMatch: "full", component: SplashComponent },
  { path: "login", pathMatch: "full", component: LoginComponent },
  { path: "logout", pathMatch: "full", component: LogoutComponent },
  { path: "register", pathMatch: "full", component: RegisterComponent },
  { path: "users", pathMatch: "full", component: UserProfileComponent },
  { path: "users/:username", pathMatch: "full", component: UserProfileComponent },
  { path: "profile", pathMatch: "full", component: UserProfileComponent },
  { path: "chat", pathMatch: "full", component: ChatComponent },
  { path: "clans", pathMatch: "full", component: ClanComponent },
  { path: "equipment", pathMatch: "full", component: EquipmentComponent },
  { path: "servers", pathMatch: "full", component: ServerComponent },
  { path: "meetups", pathMatch: "full", component: MeetupComponent },
  { path: "games", pathMatch: "full", component: GameComponent }
];

@NgModule({
  // imports: [RouterModule.forRoot(routes, {useHash: true})],
  imports: [RouterModule.forRoot(routes, { useHash: false })],
  exports: [RouterModule],
})
export class AppRoutingModule {}
