export class Server {
  id: number;
  enabled: boolean;
  name: string;
  type: string;
  ip: string;
  url: string;
  password: string;
  capacity: number;
  description: string;
  created: string;
  updated: string;

  constructor(
    id: number = 0,
    enabled: true,
    name: string = "",
    description: string = "",
    type: string = "",
    ip: string = "",
    capacity: number = 0,
    url: string = "",
    created: string = "",
    updated: string = "",
  ) {
    this.id = id;
    this.enabled = enabled;
    this.name = name;
    this.description = description;
    this.type = type;
    this.ip = ip;
    this.capacity = capacity;
    this.url = url;
    this.created = created;
    this.updated = updated;
  }
}
