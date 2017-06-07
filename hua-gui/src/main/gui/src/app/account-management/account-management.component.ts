import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-account-management',
  templateUrl: './account-management.component.html',
  styleUrls: ['./account-management.component.css']
})
export class AccountManagementComponent implements OnInit {

  private showContextMenu = false;
  private mouseLocation : {left:number,top:number} = {'left':0, 'top':0};

  constructor() { }

  ngOnInit() {

  }

  getContextMenuCss() {
    return {
      'position': 'fixed',
      'display': this.showContextMenu ? 'block' : 'none',
      'left': this.mouseLocation.left + 'px',
      'top': this.mouseLocation.top + 'px'
    }
  }

}
