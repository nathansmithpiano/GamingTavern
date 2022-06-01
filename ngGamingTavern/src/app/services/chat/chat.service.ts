import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { catchError, Observable, throwError } from "rxjs";
import { Chat } from "src/app/models/chat/chat";
import { Message } from "src/app/models/message/message";
import { environment } from "src/environments/environment";
import { AuthService } from "../auth/auth.service";

@Injectable({
  providedIn: "root",
})
export class ChatService {
  constructor(private http: HttpClient, private auth: AuthService) {}

  private url = environment.baseUrl + "api/";
  private url2 = this.url + "chats/";
  private chats: Chat[] = [];

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
    return this.http.get<Chat[]>(this.url2, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        return throwError("KABOOM");
      })
    );
  }

  getChatsByUsername(username: string): Observable<Chat[]> {
    return this.http
      .get<Chat[]>(this.url2 + 'user/' + username, {
        headers: { Authorization: "Basic " + this.auth.getCredentials() },
      })
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("KABOOM");
        })
      );
  }

  getChatById(id: number): Observable<Chat> {
    return this.http
      .get<Chat>(this.url2 + id, {
        headers: { Authorization: "Basic " + this.auth.getCredentials() },
      })
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("KABOOM");
        })
      );
  }

  sendMessage(message: Message): Observable<Message> {
    return this.http
      .post<Message>(this.url + 'messages', message, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("KABOOM");
        })
      );
  }

  deleteMessageById(id: number): Observable<void> {
    return this.http.delete<void>(this.url + 'messages/' + id, this.getHttpOptions());
    // return this.http
    //   .delete<Message>(this.url + 'messages/', id, this.getHttpOptions())
    //   .pipe(
    //     catchError((err: any) => {
    //       console.log(err);
    //       return throwError("KABOOM");
    //     })
    //   );
  }
}
