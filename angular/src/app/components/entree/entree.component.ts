import { Component, OnInit } from '@angular/core';
import {Entree} from '../../model/entree';
import {DessertService} from '../../services/dessert.service';
import {EntreeService} from '../../services/entree.service';

@Component({
  selector: 'app-entree',
  templateUrl: './entree.component.html',
  styleUrls: ['./entree.component.css']
})
export class EntreeComponent implements OnInit {
  private _entrees: Entree[];


  constructor(private _entreeService: EntreeService) { }

  ngOnInit(){
    this.initEntree();
  }

  private initEntree(){
    this._entreeService.findAll().subscribe(res => {
      this._entrees = res;
    });
  }


  get entrees(): Entree[] {
    return this._entrees;
  }

  set entrees(value: Entree[]) {
    this._entrees = value;
  }
}
