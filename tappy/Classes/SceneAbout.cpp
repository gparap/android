/*********************
 *                   *
 * Created by gparap *
 *                   *
 *********************/

#include "SceneAbout.h"
#include "SceneMainMenu.h"
#include "Globals.h"
#include "Background.h"

USING_NS_CC;

Scene *About::createScene()
{
	return About::create();
}

bool About::init()
{
	// super init first
	if (!Scene::init()) {
		return false;
	}

	// screen size and screen origin placeholders
	auto visibleSize = Director::getInstance()->getVisibleSize();
	Vec2 visibleOrigin = Director::getInstance()->getVisibleOrigin();

	// create the background
	auto background = Sprite::create("background.png");
	background->setScale(Director::getInstance()->getContentScaleFactor());
	background->setPosition(visibleSize.width / 2 + visibleOrigin.x, visibleSize.height / 2 + visibleOrigin.y);
	this->addChild(background, LAYER_BACKGROUND);

	char *strCredits = " Graphics/Font: www.kenney.nl (Kenney) \
\n Blank Buttons: https://opengameart.org/users/plemuzic \
\n PhysicsEditor: https://www.codeandweb.com \
\n  \
\n Game powered by cocos2d-x: \
\n http://www.cocos2d-x.org \
\n https://www.cocos.com \
\n  \
\n cocos2d-x is under MIT licence: \
\n see https://github.com/cocos2d/cocos2d-x/tree/v4/licenses \
\n ";

	//adjust text size
	cocos2d::Size winSize = Director::getInstance()->getWinSize();
	auto textSize=15;
	if (winSize.width >= 500.0f || winSize.height >= 500.0f) {
		textSize = 20;
	}

	// add "Touch to Play" label
 	labelAbout = Label::createWithTTF(strCredits,"fonts/Kenney Pixel.ttf", textSize);
	labelAbout->setPosition(
			visibleOrigin.x/2 + visibleSize.width / 3,
			visibleOrigin.y / 2 + visibleSize.height / 2);
	labelAbout->setColor(cocos2d::Color3B::BLACK);
	labelAbout->setAnchorPoint(Vec2::ANCHOR_MIDDLE_LEFT);
	this->addChild(labelAbout, 1);

	// add scroll menu
	auto scrollMenu = Menu::create();
	scrollMenu->setPosition(Point::ZERO);
	this->addChild(scrollMenu);

	// add main menu
	cocos2d::Vec2 origin = Director::getInstance()->getVisibleOrigin();
	auto mainMenu = Menu::create();
	mainMenu->setPosition(Point::ZERO);
	this->addChild(mainMenu);

	// add menu button
	auto menuButton = MenuItemImage::create("button-menu.png", "button-menu-pressed.png",
											CC_CALLBACK_1(About::transitToMenuScene, this));
	menuButton->setScale(Director::getInstance()->getContentScaleFactor());
	menuButton->setPosition(visibleSize.width/2 + origin.x,
							origin.y + menuButton->getContentSize().height + menuButton->getContentSize().height / 2);
	mainMenu->addChild(menuButton);

	return true;
}

void About::transitToMenuScene(Ref * pSender)
{
	// goto main menu
	auto scene = MainMenu::createScene();
	Director::getInstance()->replaceScene(TransitionFade::create(TRANSITION_FADEOUT, scene));
}
