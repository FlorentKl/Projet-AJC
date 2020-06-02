import { Component, OnInit } from '@angular/core';
import {Boisson} from '../../model/boisson';
import {BoissonService} from '../../services/boisson.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-boisson',
  templateUrl: './boisson.component.html',
  styleUrls: ['./boisson.component.css']
})
export class BoissonComponent implements OnInit {
  private _boissons: Boisson[];

  constructor(private boissonService: BoissonService) { }

  ngOnInit(){
    this.initBoissons();
  }

  private initBoissons(){
    this.boissonService.findAll().subscribe( res => {
      this._boissons = res;
    });
  }

  get boissons(): Boisson[] {
    return this._boissons;
  }

  set boissons(value: Boisson[]) {
    this._boissons = value;
  }
}
