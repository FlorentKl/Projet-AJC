import { Component, OnInit } from '@angular/core';
import {Dessert} from '../../model/dessert';
import {BoissonService} from '../../services/boisson.service';
import {DessertService} from '../../services/dessert.service';

@Component({
  selector: 'app-dessert',
  templateUrl: './dessert.component.html',
  styleUrls: ['./dessert.component.css']
})
export class DessertComponent implements OnInit {
  private _desserts: Dessert[];

  constructor(private dessertService: DessertService) { }

  ngOnInit(){
    this.initDesserts();
  }

  private initDesserts(){
    this.dessertService.findAll().subscribe( res => {
      this._desserts = res;
    });
  }

  get desserts(): Dessert[] {
    return this._desserts;
  }

  set desserts(value: Dessert[]) {
    this._desserts = value;
  }
}
