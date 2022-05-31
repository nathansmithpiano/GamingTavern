import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Server } from "src/app/models/server/server";
import { DatePipe } from "@angular/common";
import { environment } from "src/environments/environment";
import { catchError, Observable, throwError } from "rxjs";
import { AuthService } from "../auth/auth.service";

@Injectable({
  providedIn: "root",
})
export class ServerService {
  constructor(
    private http: HttpClient,
    private datePipe: DatePipe,
    private auth: AuthService
  ) {}

  private url = environment.baseUrl + "api/servers";
  private url2 = this.url + "/";
  private equipments: Server[] = [];

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: "Basic " + this.auth.getCredentials(),
        "X-Requested-With": "XMLHttpRequest",
      },
    };
    return options;
  }

      index(): Observable<Server []> {
    return this.http.get<Server[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        return throwError("ERROR retrieving equipment list");
      })
    );
  }

  show(id: number): Observable<Server> {
    return this.http
      .get<Server>(this.url2 + id, {
        headers: { Authorization: "Basic " + this.auth.getCredentials() },
      })
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("KABOOM");
        })
      );
  }

  // showByUsername(username: string): Observable<Equipment> {
  //   return this.http
  //     .get<User>(environment.baseUrl + username + '/' + username, {
  //       headers: { Authorization: "Basic " + this.auth.getCredentials() },
  //     })
  //     .pipe(
  //       catchError((err: any) => {
  //         console.log(err);
  //         return throwError("KABOOM");
  //       })
  //     );
  // }
}
