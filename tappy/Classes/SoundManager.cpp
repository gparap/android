/*********************
 *                   *
 * Created by gparap *
 *                   *
 *********************/

#include "SoundManager.h"
#include "audio/include/AudioEngine.h"
#include "Globals.h"

USING_NS_CC;


SoundManager *SoundManager::pInstance = NULL;

SoundManager* SoundManager::Instance()
{
	if (pInstance == NULL) {
		pInstance = new SoundManager;
	}
	return pInstance;
}

void SoundManager::init()
{
	musicVolume = 0.5f;
	sfxVolume = 0.5f;
}

void SoundManager::PreloadSFX()
{
	AudioEngine::preload("sfx/fly.mp3");
	AudioEngine::preload("sfx/hit.mp3");
}

void SoundManager::PreloadMusic()
{
	AudioEngine::preload("music.mp3");
}

void SoundManager::PlaySFX(const std::string &filePath)
{
	soundID = AudioEngine::play2d(filePath);
	AudioEngine::setVolume(soundID, sfxVolume);
	AudioEngine::setLoop(soundID, false);
}

void SoundManager::PlayMusic()
{
	soundID = AudioEngine::play2d("music.mp3");
	AudioEngine::setVolume(soundID, musicVolume);
	AudioEngine::setLoop(soundID, true);
}

void SoundManager::StopMusic()
{
	AudioEngine::stopAll();
}
