import { Clan } from "src/app/models/clan/clan";
import { DatePipe } from "@angular/common";
import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { AuthService } from "../auth/auth.service";
import { catchError, throwError, Observable } from "rxjs";
import { environment } from "src/environments/environment";

@Injectable({
  providedIn: "root",
})
export class ClanService {
  constructor(
    private http: HttpClient,
    private datePipe: DatePipe,
    private auth: AuthService
  ) {}

  private url = environment.baseUrl + "api/clans";
  private url2 = this.url + "/";
  private clans: Clan[] = [];

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: "Basic " + this.auth.getCredentials(),
        "X-Requested-With": "XMLHttpRequest",
      },
    };
    return options;
  }

  public index(): Observable<Clan[]> {
    return this.http.get<Clan[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        return throwError("KABOOM");
      })
    );
  }

  show(id: number): Observable<Clan> {
    return this.http
      .get<Clan>(this.url2 + id, {
        headers: { Authorization: "Basic " + this.auth.getCredentials() },
      })
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("KABOOM");
        })
      );
  }

  showByName(name: string): Observable<Clan> {
    return this.http
      .get<Clan>(environment.baseUrl + name + "/" + name, {
        headers: { Authorization: "Basic " + this.auth.getCredentials() },
      })
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("KABOOM");
        })
      );
  }

  getClanData() {
    return this.http
      .get<any>(this.url + "/data", {
        headers: { Authorization: "Basic " + this.auth.getCredentials() },
      })
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("KABOOM");
        })
      );
  }

  public create(clan: Clan) {
    return this.http.post<Clan>(this.url, clan, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError("KABOOM");
      })
    );
  }

  update(updateClan: Clan, clanId: number ) {
    const httpOptions = {
      headers: {
        'Content-type': 'application/json',
        Authorization: 'Basic ' + this.auth.getCredentials(),
      },
    };
    return this.http.put<Clan>(this.url + "/" + clanId, updateClan, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError("KABOOM");
      })
    );
  }

  delete(clanId: number ) {
    const httpOptions = {
      headers: {
        'Content-type': 'application/json',
        Authorization: 'Basic ' + this.auth.getCredentials(),
      },
    };
    return this.http.delete<void>(this.url + "/" + clanId, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError("KABOOM");
      })
    );
  }


}
