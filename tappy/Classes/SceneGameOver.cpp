/*********************
 *                   *
 * Created by gparap *
 *                   *
 *********************/

#include "SceneGameOver.h"
#include "SceneGamePlay.h"
#include "SceneMainMenu.h"
#include "Globals.h"
#include "ccUTF8.h"

USING_NS_CC;

unsigned int scoreFinal;

Scene* GameOver::createScene(unsigned int score)
{
	scoreFinal = score;
    return GameOver::create();
}

bool GameOver::init()
{
    // super init first
    if ( !Scene::init() )
    {
        return false;
    }

    auto visibleSize = Director::getInstance()->getVisibleSize();
    Vec2 origin = Director::getInstance()->getVisibleOrigin();

	// create the background
	auto background = Sprite::create("background.png");
	background->setScale(Director::getInstance()->getContentScaleFactor());
	background->setPosition(visibleSize.width / 2 + origin.x, visibleSize.height / 2 + origin.y);
	this->addChild(background, LAYER_BACKGROUND);

	// create a label to display the score
	auto stringScoreFinal = StringUtils::format("Score: %i", scoreFinal);
	auto labelScore = Label::createWithTTF(stringScoreFinal.c_str(), "fonts/Kenney Pixel.ttf",
										   visibleSize.height/2 * SCALE_FACTOR_SCORE);
	labelScore->setColor(Color3B::BLACK);
	labelScore->setPosition(visibleSize.width / 2, visibleSize.height - labelScore->getContentSize().height);

	// store high score (permanently)
	auto sharedPreferences = UserDefault::getInstance();
	if (scoreFinal > sharedPreferences->getIntegerForKey("HighScore"))
		sharedPreferences->setIntegerForKey("HighScore", scoreFinal);

	// create a label to display the high score
	auto stringScoreHigh = StringUtils::format("High Score: %i", sharedPreferences->getIntegerForKey("HighScore"));
	auto labelScoreHigh = Label::createWithTTF(stringScoreHigh.c_str(), "fonts/Kenney Pixel.ttf",
											   visibleSize.height / 2 * SCALE_FACTOR_SCORE);
	labelScoreHigh->setColor(Color3B::GRAY);
	labelScoreHigh->setPosition(visibleSize.width / 2, 
		visibleSize.height - labelScore->getContentSize().height - labelScoreHigh->getContentSize().height);

	// create the "PLAY" button
 	auto buttonPlay = MenuItemImage::create("button-start.png", "button-start-pressed.png", 
											CC_CALLBACK_1(GameOver::PlayAgain, this));
	buttonPlay->setScale(Director::getInstance()->getContentScaleFactor());

	// create a go to "MAIN MENU" button
	auto buttonMainMenu = MenuItemImage::create("button-menu.png", "button-menu-pressed.png",
												CC_CALLBACK_1(GameOver::TransitToMainMenu, this));
	buttonMainMenu->setScale(Director::getInstance()->getContentScaleFactor());

	// create the "GAME OVER" label
	auto labelScene = Label::createWithTTF("GAME OVER", "fonts/Kenney Pixel.ttf",
										   visibleSize.height/2 * SCALE_FACTOR_SCORE);
	labelScene->setColor(Color3B::BLACK);
	auto menuItemLabelScene = MenuItemLabel::create(labelScene);

	// create a menu and add all buttons
 	auto menu = Menu::create();
 	menu->setPosition(visibleSize.width / 2, visibleSize.height / 2);
	menu->addChild(menuItemLabelScene);
	menu->addChild(labelScore);
	menu->addChild(labelScoreHigh);
	menu->addChild(buttonPlay);
	menu->addChild(buttonMainMenu);
	menu->alignItemsVertically();
	this->addChild(menu);

	return true;
}

void GameOver::PlayAgain(Ref *pSender)
{
	auto scene = GamePlay::createScene();
	Director::getInstance()->replaceScene(scene);
}

void GameOver::TransitToMainMenu(Ref *pSender)
{
	auto scene = MainMenu::createScene();
	Director::getInstance()->replaceScene(TransitionFade::create(TRANSITION_FADEOUT, scene));
}
