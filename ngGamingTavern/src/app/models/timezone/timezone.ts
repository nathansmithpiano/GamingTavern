export class Timezone {
  id: number;
  abbreviation: string;
  offset: number;
  locale: string;
  description: string;

  constructor(
    id: number = 0,
    abbreviation: string = "",
    offset: number = 0,
    locale: string = "",
    description: string = "",
  ) {
    this.id = id;
    this.abbreviation = abbreviation;
    this.offset = offset;
    this.locale = locale;
    this.description = description;
    this.description = description;
  }
}
