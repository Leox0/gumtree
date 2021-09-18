export class Category {
  constructor(public mainCategoryName: string, public subCategories: string[]) {
    this.mainCategoryName = mainCategoryName;
    this.subCategories = subCategories;
  }
}
