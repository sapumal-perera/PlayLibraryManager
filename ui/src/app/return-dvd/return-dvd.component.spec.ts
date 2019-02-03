import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReturnDvdComponent } from './return-dvd.component';

describe('ReturnDvdComponent', () => {
  let component: ReturnDvdComponent;
  let fixture: ComponentFixture<ReturnDvdComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReturnDvdComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReturnDvdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
