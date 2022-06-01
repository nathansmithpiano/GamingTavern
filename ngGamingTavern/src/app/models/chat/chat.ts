import { Message } from "../message/message";
import { User } from "../user/user";

export class Chat {
  id: number;
  enabled: boolean;
  creatingUser: User;
  title: string;
  description: string;
  imageUrl: string;
  created: string;
  updated: string;
  messages: Message[];
  allUsers: User[];

  constructor(
    id: number = 0,
    enabled: boolean = true,
    creatingUser: User = new User(),
    title: string = '',
    description: string = '',
    imageUrl: string = '',
    created: string = '',
    updated: string = '',
    messages: Message[] = [],
    allUsers: User[] = []
  ) {
      this.id = id;
      this.enabled = enabled;
      this.creatingUser = creatingUser;
      this.title = title;
      this.description = description;
      this.imageUrl = imageUrl;
      this.created = created;
      this.updated = updated;
      this.messages = messages;
      this.allUsers = allUsers;
  }
}
