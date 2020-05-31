import { Component, OnInit } from '@angular/core';
declare var $: any;

@Component({
  selector: 'app-milestones',
  templateUrl: './milestones.component.html',
  styleUrls: ['./milestones.component.css']
})
export class MilestonesComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    $('.counter').counterUp({
      delay: 10,
      time: 2000
    });
  }

}
