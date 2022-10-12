import { Component, OnInit } from "@angular/core";
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from "@angular/forms";
import { Router } from "@angular/router";
import { NodeService } from "../node-service";
import { ActivatedRoute } from "@angular/router";

@Component({
  selector: "new-node",
  templateUrl: "./new-node.component.html",
  styleUrls: ["./new-node.component.css"],
})
export class NewNodeComponent implements OnInit {
  nodeForm!: FormGroup;
  submitted = false;
  nodeId: any;
  userOperation: any;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private nodeService: NodeService,
    private activatedRouter: ActivatedRoute
  ) {}

  ngOnInit() {
    this.nodeId = this.activatedRouter.snapshot.params["id"];
    this.userOperation = this.activatedRouter.snapshot.params["operation"];

    if (
      this.userOperation == "create-node" &&
      this.nodeId != null &&
      this.nodeId != "undefined"
    ) {
      this.nodeForm = this.formBuilder.group({
        name: ["", Validators.required],
        type: ["", Validators.required],
        value: ["", Validators.required],
      });
    } else if (
      this.userOperation == "update-node" &&
      this.nodeId != null &&
      this.nodeId != "undefined"
    ) {
      this.nodeForm = this.formBuilder.group({
        name: ["", Validators.required],
        type: ["", Validators.required],
        value: ["", Validators.required],
        createDate: ["", Validators.required],
        updateDate: ["", Validators.required],
      });

      this.nodeService
        .getNodeByID(this.activatedRouter.snapshot.params["id"])
        .then((data) => {
          this.nodeForm = new FormGroup({
            name: new FormControl(data.name),
            type: new FormControl(data.type),
            value: new FormControl(data.value),
            createDate: new FormControl(data.createDate),
          });
        });
    }
  }

  // for easy access to form fields
  get f() {
    return this.nodeForm.controls;
  }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.nodeForm.invalid) {
      alert(this.nodeForm.errors);
    } else {
      this.addOrUpdateNode();
    }
  }

  onReset() {
    this.submitted = false;
    this.nodeForm.reset();
    this.router.navigateByUrl("/home");
  }

  addOrUpdateNode() {
    if (this.userOperation == "create-node") {
      this.nodeService.createNode(this.nodeId, this.nodeForm.value);
      this.nodeForm.reset;
      this.router.navigateByUrl("/home");
    } else {
      console.log(this.nodeId);
      this.nodeService.updateNode(this.nodeId, this.nodeForm.value);
      this.nodeForm.reset;
      this.router.navigateByUrl("/home");
    }
  }
}
