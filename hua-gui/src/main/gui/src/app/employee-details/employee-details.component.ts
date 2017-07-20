import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../employee.service';
import { ActivatedRoute, Router, Params } from '@angular/router';
import 'rxjs/add/operator/finally';

@Component({
  selector: 'app-employee-details',
  templateUrl: './employee-details.component.html',
  styleUrls: ['./employee-details.component.css']
})
export class EmployeeDetailsComponent implements OnInit {

  private employeeInfo;
  private id;
  private processingInProgress = false;

  constructor(private employeeService: EmployeeService, private activatedRoute: ActivatedRoute, private router: Router) {
  }

  ngOnInit() {
    this.activatedRoute
      .queryParams
      .subscribe(params => {
        this.id = params['id'];
        this.processingInProgress = true;
        this.employeeService.readDetails(this.id)
          .finally(() => {this.processingInProgress = false;})
          .subscribe(data => {
            this.employeeInfo = data;
          },
          (err: any) => {
            if (err.status === 401) {
              this.router.navigate(['forbidden']);
            }
            if (err.status === 404) {
              this.router.navigate(['404']);
            }
          });
      });
  }

  profileImageUpload(event) {
    var reader = new FileReader();
    reader.readAsDataURL(event.srcElement.files[0]);
    var me = this;
    reader.onload = function () {
      var fileContent = reader.result;
      me.processingInProgress = true;
      me.employeeService.uploadProfileImage(me.id, { "profileImage": fileContent })
        .finally(() => {
          me.employeeInfo.employeeBasicInfo.profileImage = fileContent;
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
        });
    }
  }
}
