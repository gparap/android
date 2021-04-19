/*********************
 *                   *
 * Created by gparap *
 *                   *
 *********************/

#include "SceneSplashScreen.h"
#include "SceneMainMenu.h"
#include "Globals.h"

USING_NS_CC;

Scene* SplashScreen::createScene()
{
    return SplashScreen::create();
}

bool SplashScreen::init()
{
    // super init first
    if ( !Scene::init() )
    {
        return false;
    }

    auto visibleSize = Director::getInstance()->getVisibleSize();
    Vec2 origin = Director::getInstance()->getVisibleOrigin();

    // add a background sprite
	auto background = Sprite::create("splash.png");
    background->setScale(Director::getInstance()->getContentScaleFactor());
	background->setPosition(visibleSize.width / 2, visibleSize.height / 2);
	this->addChild(background);

    // make a transition to main menu
	this->scheduleOnce(CC_SCHEDULE_SELECTOR(SplashScreen::transitToMainMenu), TRANSITION_DELAY);

	return true;
}

void SplashScreen::transitToMainMenu(float transitionDelay)
{
	auto scene = MainMenu::createScene();
	Director::getInstance()->replaceScene(TransitionFade::create(TRANSITION_FADEOUT, scene));
}
