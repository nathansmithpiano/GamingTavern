import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { DatePipe } from "@angular/common";
import { environment } from "src/environments/environment";
import { catchError, Observable, throwError } from "rxjs";
import { AuthService } from "../auth/auth.service";
import { Alias } from "src/app/models/alias/alias";

@Injectable({
  providedIn: "root",
})
export class AliasService {
  constructor(
    private http: HttpClient,
    private datePipe: DatePipe,
    private auth: AuthService
  ) {}

  private url = environment.baseUrl + "api/aliases";
  private url2 = this.url + "/";
  private aliases: Alias[] = [];

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
    return this.http.get<Alias[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        return throwError("KABOOM");
      })
    );
  }

  show(id: number): Observable<Alias> {
    return this.http
      .get<Alias>(this.url2 + id, {
        headers: { Authorization: "Basic " + this.auth.getCredentials() },
      })
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("KABOOM");
        })
      );
  }

  showByUsername(username: string): Observable<Alias[]> {
    return this.http
      .get<Alias[]>(this.url + '/user/' + username, {
        headers: { Authorization: "Basic " + this.auth.getCredentials() },
      })
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("KABOOM");
        })
      );
  }
}
