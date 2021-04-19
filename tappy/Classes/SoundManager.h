/*********************
 *                   *
 * Created by gparap *
 *                   *
 *********************/

#ifndef __SOUND_MANAGER_H__
#define __SOUND_MANAGER_H__

#include "cocos2d.h"

class SoundManager
{
public:
	static SoundManager *Instance();
	void PreloadSFX();
	void PreloadMusic();
	void PlaySFX(const std::string &filePath);
	void PlayMusic();
	void StopMusic();
	void init();
	int soundID;
private:
	SoundManager() {};
	SoundManager(SoundManager const&) {};
	SoundManager &operator=(SoundManager const&) {};
	static SoundManager *pInstance;
	float musicVolume, sfxVolume;
};

#endif // __SOUND_MANAGER_H__
