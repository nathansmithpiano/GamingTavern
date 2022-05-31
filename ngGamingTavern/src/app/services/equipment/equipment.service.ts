import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Equipment } from "src/app/models/equipment/equipment";
import { DatePipe } from "@angular/common";
import { environment } from "src/environments/environment";
import { catchError, Observable, throwError } from "rxjs";
import { AuthService } from "../auth/auth.service";

@Injectable({
  providedIn: "root",
})
export class EquipmentService {
  constructor(
    private http: HttpClient,
    private datePipe: DatePipe,
    private auth: AuthService
  ) {}

  private url = environment.baseUrl + "api/equipments";
  private url2 = this.url + "/";
  private equipments: Equipment[] = [];

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: "Basic " + this.auth.getCredentials(),
        "X-Requested-With": "XMLHttpRequest",
      },
    };
    return options;
  }

      index(): Observable<Equipment []> {
    return this.http.get<Equipment[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        return throwError("ERROR retrieving equipment list");
      })
    );
  }

  show(id: number): Observable<Equipment> {
    return this.http
      .get<Equipment>(this.url2 + id, {
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
