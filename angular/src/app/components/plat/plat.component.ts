import { Component, OnInit } from '@angular/core';
import {Plat} from '../../model/plat';
import {EntreeService} from '../../services/entree.service';
import {PlatService} from '../../services/plat.service';

@Component({
  selector: 'app-plat',
  templateUrl: './plat.component.html',
  styleUrls: ['./plat.component.css']
})
export class PlatComponent implements OnInit {


  private _plats: Plat[];

  constructor(private platService: PlatService) { }

  ngOnInit(){
    this.initPlats();
  }

  private initPlats() {
    this.platService.findAll().subscribe(res => {
      this._plats = res;
    });
  }

    get plats(): Plat[] {
      return this._plats;
    }

    set plats(value: Plat[]) {
      this._plats = value;
    }
}
