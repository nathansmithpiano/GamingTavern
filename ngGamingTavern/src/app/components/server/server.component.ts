import { ServerService } from './../../services/server/server.service';
import { Component, OnInit } from '@angular/core';
import { Server } from 'src/app/models/server/server';

@Component({
  selector: 'app-server',
  templateUrl: './server.component.html',
  styleUrls: ['./server.component.scss']
})
export class ServerComponent implements OnInit {

  constructor(private serverSvc: ServerService) { }
  
  selected: Server | null = null;

  servers: Server[] = [];
  
  leftColumnWidth = 4;
  
  topPadding = 3;
  
  rightColumnWidth = 12 - this.leftColumnWidth;

  leftHeader = 'Servers';
  
  serverName = '';
  serverDescription = '';
  serverEnabled = '';
  serverType = '';
  serverIp = '';
  serverCapacity = 0;
  serverUrl = '';

  ngOnInit(): void {
    this.reload();
  }

  reload = (): void => {
    this.serverSvc.index().subscribe(
      data => this.servers = data,
      err => console.error(err)
    );
     }

}
