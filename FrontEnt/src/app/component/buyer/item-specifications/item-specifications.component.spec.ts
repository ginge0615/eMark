/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';
import { ReactiveFormsModule} from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';

import { ItemSpecificationsComponent } from './item-specifications.component';
import { GlobalService } from 'src/app/common/global.service';

describe('ItemSpecificationsComponent', () => {
  let component: ItemSpecificationsComponent;
  let fixture: ComponentFixture<ItemSpecificationsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItemSpecificationsComponent ],
      imports:[ 
        ReactiveFormsModule,RouterTestingModule
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItemSpecificationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
   });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should add to cart', () => {
    component.data = component.global.listItems[0];
    component.addToCart();
    expect(component.global.cartItems.length).toBe(1);

  });

  it('purchase number should be 1', () => {
    component.data = component.global.listItems[0];
    component.addToCart();
    expect(component.data.purchaseNum).toBe(1);
  });

  it('purchase number should be 2', () => {
    component.data = component.global.listItems[0];
    component.addToCart();
    component.addToCart();
    expect(component.data.purchaseNum).toBe(2);
  });
  
});
