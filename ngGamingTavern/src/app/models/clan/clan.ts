export class Clan {
    id: number;
    enabled: boolean;
    name: string;
    description: string;
    imageUrl: string;
    created: string;
    updated: string;



constructor(
    id: number= 0,
    enabled: boolean = true,
    name: string = "",
    description: string = "",
    imageUrl: string = "",
    created: string = "",
    updated: string = ""
){
    this.id = id;
    this.enabled = enabled;
    this.name = name;
    this.description = description;
    this.imageUrl = imageUrl;
    this.created = created;
    this.updated = updated;
}
}