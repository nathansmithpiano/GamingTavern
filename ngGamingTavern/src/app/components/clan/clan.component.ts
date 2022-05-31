
import { Component, OnInit } from '@angular/core';
import { ClanService } from 'src/app/services/clan/clan.service';
import { Clan } from './../../models/clan/clan';

@Component({
  selector: 'app-clan',
  templateUrl: './clan.component.html',
  styleUrls: ['./clan.component.scss']
})
export class ClanComponent implements OnInit {
  ClanService: any;

  constructor(private clanSvc: ClanService) { }
  
  selected: Clan | null = null;

  clans: Clan[] = [];
  
  leftColumnWidth = 4;
  
  topPadding = 3;
  
  rightColumnWidth = 12 - this.leftColumnWidth;

  leftHeader = 'Clans';
  
  clanName = '';
  clanDescription = '';
  clanModel = '';
  clanImageUrl = '';
  clanCreated = '';
  clanUpdated = '';

  clanData : [] = [];

  newClan: Clan = new Clan(); 

  showClan(id : number){
    this.clanSvc.show(id).subscribe(
      data => {this.clanName = data.name; this.clanDescription = data.description; this.clanImageUrl = data.imageUrl; this.clanCreated = data.created; this.clanUpdated = data.updated},
      err => console.error(err)
    );
  }
  
  onSubmit(){
    // this.product.push(this.newProduct)
    this.ClanService.create(this.newClan)
    this.newClan = new Clan()
    this.clans = this.ClanService.index();
  }
 

  ngOnInit(): void {
    // this.reload();
    this.loadData();
  }

  loadData = (): void =>{
    this.clanSvc.getClanData().subscribe(
    data => {this.clanData = data},
    err => console.error(err)
    );
  }

  reload = (): void => {
   this.clanSvc.index().subscribe(
     data => { this.clans = data},
     err => console.error(err)
   );
    // this.clanSvc.show(1).subscribe(
    // data => console.log(data)
    // );
    }



}