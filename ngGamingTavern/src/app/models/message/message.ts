import { Chat } from "../chat/chat";
import { User } from "../user/user";

export class Message {
  id: number;
  chat: Chat;
  fromUser: User;
  replyingToMessage: Message;
  contents: string;
  created: string;
  replies: Message[];

  constructor(
    id: number = 0,
    chat: Chat = null,
    fromUser: User = null,
    replyingToMessage: Message = null,
    contents: string = "",
    created: string = "",
    replies: Message[] = []
  ) {
    this.id = id;
    this.chat = chat;
    this.fromUser = fromUser;
    this.replyingToMessage = replyingToMessage;
    this.contents = contents;
    this.created = created;
    this.replies = replies;
  }
}
