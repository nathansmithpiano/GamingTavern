import { ChatComponent } from './components/chat/chat/chat.component';
import { ClanComponent } from "./components/clan/clan.component";
import { MeetupComponent } from "./components/meetup/meetup.component";
import { ServerComponent } from "./components/server/server.component";
import { EquipmentComponent } from "./components/equipment/equipment.component";
import { LogoutComponent } from "./components/auth/logout/logout.component";
import { HomeComponent } from "./components/home/home.component";
import { LoginComponent } from "./components/auth/login/login.component";
import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { UserProfileComponent } from "./components/user/profile/user-profile/user-profile.component";
import { GameComponent } from "./components/game/game.component";

const routes: Routes = [
  { path: "", pathMatch: "full", component: HomeComponent },
  { path: "users", pathMatch: "full", component: UserProfileComponent },
  { path: "users/:username", pathMatch: "full", component: UserProfileComponent},
  { path: "login", pathMatch: "full", component: LoginComponent },
  { path: "logout", pathMatch: "full", component: LogoutComponent },
  { path: "profile", pathMatch: "full", component: UserProfileComponent },
  { path: "clans", pathMatch: "full", component: ClanComponent },
  { path: "equipment", pathMatch: "full", component: EquipmentComponent },
  { path: "server", pathMatch: "full", component: ServerComponent },
  { path: "meetup", pathMatch: "full", component: MeetupComponent },
  { path: "game", pathMatch: "full", component: GameComponent },
  { path: "chat", pathMatch: "full", component: ChatComponent }
];

@NgModule({
  // imports: [RouterModule.forRoot(routes, {useHash: true})],
  imports: [RouterModule.forRoot(routes, { useHash: false })],
  exports: [RouterModule],
})
export class AppRoutingModule {}
