import { EquipmentService } from "./../../services/equipment/equipment.service";
import { Equipment } from "./../../models/equipment/equipment";
import { Component, OnInit } from "@angular/core";

@Component({
  selector: "app-equipment",
  templateUrl: "./equipment.component.html",
  styleUrls: ["./equipment.component.scss"],
})
export class EquipmentComponent implements OnInit {
  constructor(private equipmentSvc: EquipmentService) {}

  selected: Equipment | null = null;

  equipments: Equipment[] = [];

  leftColumnWidth = 4;

  topPadding = 3;

  rightColumnWidth = 12 - this.leftColumnWidth;

  leftHeader = "Equipment";

  equipName = "";
  equipDescription = "";
  equipModel = "";

  ngOnInit(): void {
    this.reload();
  }

  reload = (): void => {
    this.equipmentSvc.index().subscribe(
      (data) => (this.equipments = data),
      (err) => console.error(err)
    );
  };
}
