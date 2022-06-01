import { Alias } from './../alias/alias';
export class Clan {
    id: number | null;
    creatorAlias: Alias;
    enabled: boolean;
    name: string;
    description: string;
    imageUrl: string;
    created: string;
    updated: string;



constructor(
    id: number | null = null,
    creatorAlias: Alias = null,
    enabled: boolean = true,
    name: string = "",
    description: string = "",
    imageUrl: string = "",
    created: string = "",
    updated: string = ""
){
    this.id = id;
    this.creatorAlias = creatorAlias;
    this.enabled = enabled;
    this.name = name;
    this.description = description;
    this.imageUrl = imageUrl;
    this.created = created;
    this.updated = updated;
}
}