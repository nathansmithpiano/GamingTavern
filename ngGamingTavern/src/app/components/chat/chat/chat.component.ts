import { ChatService } from "./../../../services/chat/chat.service";
import { Component, OnInit } from "@angular/core";
import { Chat } from "src/app/models/chat/chat";
import { User } from "src/app/models/user/user";
import { UserService } from "src/app/services/user/user.service";
import { AuthService } from "src/app/services/auth/auth.service";
import { Message } from "src/app/models/message/message";
import { GlobalComponent } from "src/app/global-component";

@Component({
  selector: "app-chat",
  templateUrl: "./chat.component.html",
  styleUrls: ["./chat.component.scss"],
})
export class ChatComponent implements OnInit {
  constructor(
    private auth: AuthService,
    private chatService: ChatService,
    private userService: UserService
  ) {}

  // global styling - make sure to add to top of page:
  // import { GlobalComponent } from "src/app/global-component";
  gridItemClass = GlobalComponent.gridItemClass;
  rippleColor = GlobalComponent.rippleColor;
  customRounding = GlobalComponent.customRounding;

  loggedInUser: User = new User();
  chats: Chat[] = [];
  usernames: string[];
  selectedChat: Chat;
  newMessage: Message = new Message();
  timerStarted: boolean = false;
  allChatUsers: User[] = [];

  // html settings
  topPadding = 3;
  rounding: number = 5;
  leftSummaryClass = "text-center text-muted";

  ngOnInit(): void {
    this.getUserByUsername(this.auth.getCurrentUsername());
  }

  reload() {
    this.getChatsByUsername(this.loggedInUser.username);
  }

  refreshChat() {
    this.refreshSelectedChat();
  }

  selectChat(chat: Chat) {
    this.selectedChat = chat;
    this.allChatUsers = chat.allUsers;
    this.sortSelectedChat();
    // refresh on time interval, start only once
    let seconds = 3;
    if (!this.timerStarted) {
      setInterval(() => {
        if (this.selectedChat) {
          this.refreshSelectedChat();
          this.timerStarted = true;
        }
      }, seconds * 1000);
    }
  }

  sortSelectedChat() {
    let messageDates: Date[] = [];
    for (let message of this.selectedChat.messages) {
      messageDates.push(new Date(message.created));
      // let tempMessage = new Message();
      // tempMessage = Object.assign(tempMessage, message);
    }
    const sortedDates = messageDates
      .slice()
      .sort((a, b) => a.getTime() - b.getTime());

    let newMessages: Message[] = [];
    for (let date of sortedDates) {
      for (let message of this.selectedChat.messages) {
        if (date.toISOString() === new Date(message.created).toISOString()) {
          newMessages.push(message);
        }
      }
    }
    this.selectedChat.messages = newMessages;
  }

  sendMessage() {
    console.log(this.newMessage);
    this.newMessage.fromUser = this.loggedInUser;
    this.newMessage.fromUser = this.loggedInUser;
    this.newMessage.chat = this.selectedChat;
    this.newMessage.replyingToMessage = null;
    this.newMessage.created = new Date().toISOString();
    console.log(this.newMessage);

    this.chatService.sendMessage(this.newMessage).subscribe(
      (data) => {
        this.newMessage = new Message();
        this.refreshChat();
      },
      (err) => {
        console.log(
          "ChatComponent sendMessage(): Observable got an error " + err
        );
      }
    );
  }

  deleteMessage(message: Message) {
    console.log('deleting');
    this.chatService.deleteMessageById(message.id).subscribe(
      (data) => {
        this.refreshSelectedChat();
      },
      (err) => {
        console.log(
          "UserProfileComponent deleteMessage(): Observable got an error " +
            err
        );
      }
    );
  }

  getChatsByUsername(username: string) {
    this.chatService.getChatsByUsername(username).subscribe(
      (data) => {
        this.chats = data;
      },
      (err) => {
        console.log(
          "UserProfileComponent reload().aliasService.showByUsername(): Observable got an error " +
            err
        );
      }
    );
  }

  refreshSelectedChat() {
    this.chatService.getChatById(this.selectedChat.id).subscribe(
      (data) => {
        this.selectedChat = data;
        this.sortSelectedChat();
      },
      (err) => {
        console.log(
          "UserProfileComponent reload().aliasService.showByUsername(): Observable got an error " +
            err
        );
      }
    );
  }

  getUserByUsername(username: string) {
    this.userService.getUserByUsername(username).subscribe(
      (data) => {
        this.loggedInUser = data;
        this.reload();
      },
      (err) => {
        console.log(
          "UserProfileComponent show(): Observable got an error " + err
        );
      }
    );
  }
}
