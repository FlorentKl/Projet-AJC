import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MontestComponent } from './montest.component';

describe('MontestComponent', () => {
  let component: MontestComponent;
  let fixture: ComponentFixture<MontestComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MontestComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MontestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
