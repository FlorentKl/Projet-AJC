import { Component, OnInit } from '@angular/core';
import {RecetteService} from '../../services/recette.service';

@Component({
  selector: 'app-recherche',
  templateUrl: './recherche.component.html',
  styleUrls: ['./recherche.component.css']
})
export class RechercheComponent implements OnInit {

  constructor(private recetteService: RecetteService) { }

  ngOnInit() {
  }

}
