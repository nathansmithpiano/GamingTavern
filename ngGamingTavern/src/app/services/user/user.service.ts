import { Meetup } from './../../models/meetup/meetup';
import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { User } from "src/app/models/user/user";
import { environment } from "src/environments/environment";
import { catchError, Observable, throwError } from "rxjs";
import { AuthService } from "../auth/auth.service";
import { Game } from "src/app/models/game/game";

@Injectable({
  providedIn: "root",
})
export class UserService {
  constructor(
    private http: HttpClient,
    private auth: AuthService
  ) {}

  private url = environment.baseUrl + "api";
  private url2 = this.url + "/users/";
  private users: User[] = [];

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: "Basic " + this.auth.getCredentials(),
        "X-Requested-With": "XMLHttpRequest",
      },
    };
    return options;
  }

  public index() {
    return this.http.get<User[]>(this.url2, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        return throwError("KABOOM");
      })
    );
  }

  getUserByUsername(username: string): Observable<User> {
    return this.http
      .get<User>(this.url2 + username, {
        headers: { Authorization: "Basic " + this.auth.getCredentials() },
      })
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("KABOOM");
        })
      );
  }

  getFriendsByUsername(username: string): Observable<User[]> {
    return this.http
      .get<User[]>(this.url2 + username + '/friends', {
        headers: { Authorization: "Basic " + this.auth.getCredentials() },
      })
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("KABOOM");
        })
      );
  }

  getBlockedUsersByUsername(username: string): Observable<User[]> {
    return this.http
      .get<User[]>(this.url2 + username + '/blockedusers', {
        headers: { Authorization: "Basic " + this.auth.getCredentials() },
      })
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("KABOOM");
        })
      );
  }

  getGamesByUsername(username: string): Observable<Game[]> {
    return this.http
      .get<Game[]>(this.url2 + username + '/games', {
        headers: { Authorization: "Basic " + this.auth.getCredentials() },
      })
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("KABOOM");
        })
      );
  }

  getLocationsByUsername(username: string): Observable<Location[]> {
    return this.http
      .get<Location[]>(this.url2 + username + '/locations', {
        headers: { Authorization: "Basic " + this.auth.getCredentials() },
      })
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("KABOOM");
        })
      );
  }

  getMeetupsByUsername(username: string): Observable<Meetup[]> {
    return this.http
      .get<Meetup[]>(this.url2 + username + '/meetups', {
        headers: { Authorization: "Basic " + this.auth.getCredentials() },
      })
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("KABOOM");
        })
      );
  }

  createUser(newUser: User){
    newUser.completed = false;
    return this.http.post<User>(this.url, newUser).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('KABOOM');
      })
    );

  }

  updateUser(updateUser: User): Observable<User> {
    if(updateUser.completed) {
      let tempDate = this.datePipe.transform(Date.now(), 'shortDate');
      if(tempDate !== null){
        updateUser.completeDate = tempDate;
      }
    } else {
      updateUser.completeDate = '';
    }
    const httpOptions = {
      headers: {
        'Content-type': 'application/json',
        Authorization: 'Basic ' + this.auth.getCredentials(),
      },
    };
    return this.http.put<User>(this.url2 + updateUser.id, updateUser, {
      headers: { Authorization: "Basic " + this.auth.getCredentials() }
    })
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('KABOOM');
      })
    );
  
  }
}
