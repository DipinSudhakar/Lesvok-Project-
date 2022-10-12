import { NestedTreeControl } from "@angular/cdk/tree";
import { Component, OnInit } from "@angular/core";
import { MatTreeNestedDataSource } from "@angular/material/tree";
import { Router } from "@angular/router";
import { NodeService } from "../node-service";

interface DefaultNode {
  id: String;
  name: String;
  type: String;
  createdDate: String;
  updatedDate: String;
  value: number;
  children?: DefaultNode[];
}

@Component({
  selector: "app-home",
  templateUrl: "./home.component.html",
  styleUrls: ["./home.component.css"],
})
export class HomeComponent implements OnInit {
  nestedDataSource = new MatTreeNestedDataSource<DefaultNode>();
  tree_single_detail: any = [];
  createNodeFlag = "/create-node";
  updateNodeFlag = "/update-node";

  nestedTreeControl = new NestedTreeControl<DefaultNode>(
    (node) => node.children
  );

  constructor(private nodeService: NodeService, private router: Router) {}

  ngOnInit(): void {
    this.nodeService.getNodesFromDb().then((data) => {
      this.nestedDataSource.data = data;
    });
  }

  // check if any child is present
  hasNestedChild(index: number, node: any) {
    return node.children?.length > 0;
  }

  selectNode(node: any) {
    this.tree_single_detail = [];
    this.tree_single_detail = node;
  }

  directToCreatePage() {
    if (this.tree_single_detail._id == null) {
      alert("PLease select a node");
    } else {
      this.router.navigateByUrl(
        "/node-form/" + this.tree_single_detail._id + this.createNodeFlag
      );
    }
  }
  directToUpdatePage() {
    if (this.tree_single_detail._id == null) {
      alert("PLease select a node");
    } else {
      this.router.navigateByUrl(
        "/node-form/" + this.tree_single_detail._id + this.updateNodeFlag
      );
    }
  }

  deleteNode = () => {
    if (this.tree_single_detail._id != null) {
      if (this.tree_single_detail._id == "6345376b0fb9645ec5d5715a") {
        alert("Cant delete root node");
      } else {
        this.nodeService.deleteNode(this.tree_single_detail._id);
        location.reload();
      }
    } else {
      alert("No nodes selected!");
    }
  };
}
