import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../services/employee.service';
import { ActivatedRoute, Router, Params } from "@angular/router";

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css'],
  providers: [EmployeeService]
})
export class UserDetailsComponent implements OnInit {

  private employeeId = localStorage.getItem("userName");
  private employeeInfo;
  private processingInProgress = false;

  constructor(private employeeService: EmployeeService,
              private router: Router) {
  }

  ngOnInit() {
    this.employeeService.readDetails(this.employeeId)
    .subscribe(data => {
      this.employeeInfo = data;
      console.log("Employee Service Read Response : "+JSON.stringify(this.employeeInfo));
    });
  }

  profileImageUpload(event) {
    var reader = new FileReader();
    reader.readAsDataURL(event.srcElement.files[0]);
    var me = this;
    reader.onload = function () {
      var fileContent = reader.result;
      me.processingInProgress = true;
      me.employeeService.uploadProfileImage(me.employeeId, { "profileImage": fileContent })
        .finally(() => {
          me.processingInProgress = false;
        }
        )
        .subscribe(data => {
        },
        (err: any) => {
          if (err.status === 401) {
            me.router.navigate(['forbidden']);
          }
          if (err.status === 404) {
            me.router.navigate(['404']);
          }
        },
        () => {
          me.employeeInfo.employeeBasicInfo.profileImage = fileContent;
        });
    }
  }

}
