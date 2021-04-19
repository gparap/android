/*********************
 *                   *
 * Created by gparap *
 *                   *
 *********************/

#include "SceneMainMenu.h"
#include "Globals.h"
#include "SceneGamePlay.h"
#include "SceneAbout.h"

USING_NS_CC;

Scene *MainMenu::createScene()
{
    return MainMenu::create();
}

bool MainMenu::init()
{
    // super init first
    if (!Scene::init()) {
        return false;
    }

    auto visibleSize = Director::getInstance()->getVisibleSize();
    Vec2 origin = Director::getInstance()->getVisibleOrigin();

    // create the background
    auto background = Sprite::create("background.png");
    background->setScale(Director::getInstance()->getContentScaleFactor());
    background->setPosition(visibleSize.width / 2 + origin.x, visibleSize.height / 2 + origin.y);
    this->addChild(background, LAYER_BACKGROUND);

    // create "START" button
    auto startButton = MenuItemImage::create("button-start.png", "button-start-pressed.png",
                                             CC_CALLBACK_1(MainMenu::transitToGameScene, this));
    startButton->setScale(Director::getInstance()->getContentScaleFactor());

    // create "ABOUT" button
    auto aboutButton = MenuItemImage::create("button-about.png", "button-about-pressed.png",
                                             CC_CALLBACK_1(MainMenu::transitToAboutScene, this));
    aboutButton->setScale(Director::getInstance()->getContentScaleFactor());

    // create "QUIT" button
    auto quitButton = MenuItemImage::create("button-quit.png", "button-quit-pressed.png",
                                            CC_CALLBACK_1(MainMenu::ExitGame, this));
    quitButton->setScale(Director::getInstance()->getContentScaleFactor());

    // create main menu and add buttons
    auto mainMenu = Menu::create();
    mainMenu->setPosition(visibleSize.width / 2, visibleSize.height / 2);
    mainMenu->addChild(startButton);
    mainMenu->addChild(aboutButton);
    mainMenu->addChild(quitButton);
    mainMenu->alignItemsVertically();
    this->addChild(mainMenu);

    return true;
}

void MainMenu::transitToGameScene(Ref *pSender)
{
    auto scene = GamePlay::createScene();
    Director::getInstance()->replaceScene(TransitionFade::create(TRANSITION_FADEOUT, scene));
}

void MainMenu::transitToAboutScene(Ref *pSender)
{
    auto scene = About::createScene();
    Director::getInstance()->replaceScene(TransitionFade::create(TRANSITION_FADEOUT, scene));
}

void MainMenu::ExitGame(Ref *pSender)
{
    Director::getInstance()->end();
}
