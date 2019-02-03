import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BorrowDvdComponent } from './borrow-dvd.component';

describe('BorrowDvdComponent', () => {
  let component: BorrowDvdComponent;
  let fixture: ComponentFixture<BorrowDvdComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BorrowDvdComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BorrowDvdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
