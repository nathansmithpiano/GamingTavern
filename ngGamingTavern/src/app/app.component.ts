import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'Gaming Tavern';

  public static bgColor: string = 'hsl(218, 41%, 25%);';
}
