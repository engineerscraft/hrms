import { ScxGuiPage } from './app.po';

describe('scx-gui App', () => {
  let page: ScxGuiPage;

  beforeEach(() => {
    page = new ScxGuiPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
