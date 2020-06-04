import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MesrecettesComponent } from './mesrecettes.component';

describe('MesrecettesComponent', () => {
  let component: MesrecettesComponent;
  let fixture: ComponentFixture<MesrecettesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MesrecettesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MesrecettesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
