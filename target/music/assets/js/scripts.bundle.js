"use strict";

//=> Class Definition
var Analytics = Analytics || {};

$(function () {
    Analytics = {
        //=> Initialize function to call all functions of the class
        init: function () {
            Analytics.user();
            Analytics.song();
            Analytics.purchase();
            Analytics.statistics();
        },

        //=> User chart
        user: function () {
            if ($('#user').length === 0) {
                return false;
            }

            var userEl = document.getElementById('user').getContext('2d');
            var data = {
                labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul'],
                datasets: [{
                    label: 'Songs',
                    data: [65, 59, 42, 73, 56, 55, 40],
                    backgroundColor: '#f11717',
                    borderColor: '#f11717',
                    borderWidth: 3,
                    pointBorderWidth: 0,
                    pointRadius: 0
                }]
            };
            var options = {
                responsive: true,
                legend: {
                    display: false
                },
                scales: {
                    yAxes: [{
                        display: false,
                        ticks: {
                            min: 0,
                            max: 85
                        }
                    }],
                    xAxes: [{
                        display: false
                    }]
                },
                layout: {
                    padding: 0,
                    margin: 0
                }
            };
            var myLineChart = new Chart(userEl, {
                type: 'line',
                data: data,
                options: options
            });
        },

        //=> Song chart
        song: function () {
            if ($('#songChart').length === 0) {
                return false;
            }

            var songEl = document.getElementById('songChart').getContext('2d');
            var data = {
                labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul'],
                datasets: [{
                    label: 'Songs',
                    data: [65, 59, 42, 73, 56, 55, 40],
                    backgroundColor: '#00c746',
                    borderColor: '#00c746',
                    borderWidth: 3,
                    pointBorderWidth: 0,
                    pointRadius: 0
                }]
            };
            var options = {
                responsive: true,
                legend: {
                    display: false
                },
                scales: {
                    yAxes: [{
                        display: false,
                        ticks: {
                            min: 0,
                            max: 85
                        }
                    }],
                    xAxes: [{
                        display: false,
                        barPercentage: 0.5
                    }]
                },
                layout: {
                    padding: 0,
                    margin: 0
                }
            };
            var myLineChart = new Chart(songEl, {
                type: 'bar',
                data: data,
                options: options
            });
        },

        //=> Purchase chart
        purchase: function () {
            if ($('#purchase').length === 0) {
                return false;
            }

            var userEl = document.getElementById('purchase').getContext('2d');
            var data = {
                labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul'],
                datasets: [{
                    label: 'Songs',
                    data: [65, 59, 42, 73, 56, 55, 40],
                    backgroundColor: 'transparent',
                    borderColor: '#222629',
                    borderWidth: 3,
                    pointBorderWidth: 0,
                    pointRadius: 0
                }]
            };
            var options = {
                responsive: true,
                legend: {
                    display: false
                },
                scales: {
                    yAxes: [{
                        display: false,
                        ticks: {
                            min: 0,
                            max: 85
                        }
                    }],
                    xAxes: [{
                        display: false,
                        barPercentage: 0.5
                    }]
                },
                layout: {
                    padding: 0,
                    margin: 0
                }
            };
            var myLineChart = new Chart(userEl, {
                type: 'line',
                data: data,
                options: options
            });
        },

        //=> Statistics chart
        statistics: function () {
            if ($('#userStatistics').length === 0) {
                return false;
            }

            var statisticsEl = document.getElementById('userStatistics').getContext('2d');
            var data = {
                labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul'],
                datasets: [{
                    label: 'User',
                    data: [65, 59, 42, 73, 56, 55, 40],
                    backgroundColor: '#ad20d4',
                    borderColor: 'transparent',
                    borderWidth: 3,
                    pointBorderWidth: 0,
                    pointRadius: 0
                }]
            };
            var options = {
                responsive: true,
                legend: {
                    display: false
                },
                scales: {
                    yAxes: [{
                        ticks: {
                            min: 0,
                            max: 80
                        }
                    }],
                    xAxes: [{
                        barPercentage: 0.3
                    }]
                },
                layout: {
                    padding: 0,
                    margin: 0
                }
            };
            var myLineChart = new Chart(statisticsEl, {
                type: 'bar',
                data: data,
                options: options
            });
        }
    };

    //=> Call class at document ready
    $(document).ready(Analytics.init);
});
"use strict";



//=> Class Definition
var AppConfig = AppConfig || {};

$(function () {
    AppConfig = {
        //=> Initialize function to call all functions of the class
        init: function () {
            AppConfig.appRouting();
            AppConfig.initAppScrollbars();
            AppConfig.langCheckedToApply();
            AppConfig.search();
            AppConfig.buttonClickEvents();
            AppConfig.initTheme();
            AppConfig.reInitFunction();
        },

        //=> Reinitialize powerful functions of app
        reInitFunction: function () {
            AppConfig.initSlickCarousel();
            AppConfig.materialTab();
            AppConfig.initCountdown();
           // AppConfig.addFavorite();
           // AudioPlayer.init(); /*todo*/
            Analytics.init();
        },

        //=> Handle app routing when page url change
        appRouting: function () {

            var _this = $(this);
            var url = _this.attr('href') !== 'undefined' ? _this.attr('href') : null;


            var $navLink = $('#sidebar .nav-link');
            $navLink.removeClass('active');
            $navLink.each(function () {
                if (url === _this.attr('href')) {
                    _this.addClass('active');
                }
            })


           /* var $document = $(document);
            $document.on('click', 'a:not(.load-page):not(.external)', function (e) {

var _this = $(this);
                var url = _this.attr('href') !== 'undefined' ? _this.attr('href') : null;


                var $navLink = $('#sidebar .nav-link');
                $navLink.removeClass('active');
                $navLink.each(function () {
                    if (url === _this.attr('href')) {
                        _this.addClass('active');
                    }
                })


                //e.preventDefault();
            });*/
/*
            var $document = $(document);
            $document.on('click', 'a:not(.load-page):not(.external)', function () {


                /!*var _this = $(this);
                var url = _this.attr('href') !== 'undefined' ?  _this.attr('href') : null ;
                if (url && AppConfig.filterLink(url)) {
                    AppConfig.ajaxLoading(url);
                }*!/
                var _this = $(this);
                var url = _this.attr('href') !== 'undefined' ?  _this.attr('href') : null ;

                var $navLink = $('#sidebar .nav-link');
                $navLink.removeClass('active');
                $navLink.each(function () {
                    if (url === $(this).attr('href')) {
                        $(this).addClass('active');
                    }
                })
            });*/

           /* var $document = $(document);
            $document.on('click', 'a:not(.load-page):not(.external)', function (e) {
                e.preventDefault();


                var _this = $(this);
                var url = _this.attr('href') !== 'undefined' ?  _this.attr('href') : null ;
                if (url && AppConfig.filterLink(url)) {
                   AppConfig.ajaxLoading(url);
                }


            });*/

        },

        //=> Filter link a page link or not
        filterLink: function (link) {
            if(link === null) {
                return false;
            } else if(link.substr(0, 1) === '#') {
                return false;
            } else if(link.length >= 10 && link.substr(0,10).toLowerCase() === 'javascript') {
                return false;
            } else if(link.length < 1) {
                return false;
            }

            return true;
        },

        //=> Ajax loading for html pages
        ajaxLoading: function (url) {
            var History = window.history;
            History.pushState("", "", url);

            $.ajax({
                url: url,
                context: document.body
            }).done(function (response) {
                var content = $('<div>' + response + '</div>');
              //  changeTitle(content);
              // replaceImageBanner(content);
              //  replaceContent(content);
                  setActiveClass();
            }).fail(function(jqXHR, textStatus){
                alert('Something went wrong. Please try again');
                return false;
            });

            // Change old title with new one
            function changeTitle(newContent) {
                $('head title').html(newContent.find('title').html());
            }

            // Replace old page banner with new one
            function replaceImageBanner(newContent) {
                var $banner = $('.banner');
                var bannerClass = $banner.attr('class');
                $banner.removeClass(bannerClass.split(' ')[1]);
                $banner.addClass(newContent.find('.banner').attr('class'));
            }

            // Replace old page html with new one
            function replaceContent(newContent) {
                $('#appRoute').html(newContent.find('#appRoute').html());
                $('#wrapper').animate({scrollTop:0}, 'fast');
                Analytics.init();
                AppConfig.reInitFunction();
            }

            // Set active class on nav link when page url change
            function setActiveClass() {
                var $navLink = $('#sidebar .nav-link');
                $navLink.removeClass('active');
                $navLink.each(function () {
                    if (url === $(this).attr('href')) {
                        $(this).addClass('active');
                    }
                })
            }
        },

        //=> Initialize theme settings
        initTheme: function () {
            // $('body').themeSettings();
        },

        //=> Languages checked unchecked for apply button disable and enable
        langCheckedToApply: function () {
            var $langCheckbox = $('#lang .custom-control-input');
            $langCheckbox.on('change', function () {
                $('#langApply').prop('disabled', !$langCheckbox.filter(':checked').length);
            }).trigger('change');
        },

        //=> Initialize app scrollbars
        initAppScrollbars: function () {
            $('[data-scrollable="true"]').each(function () {
                var ps = new PerfectScrollbar(this, {
                    wheelSpeed: 0.5,
                    swipeEasing: true,
                    wheelPropagation: false,
                    minScrollbarLength: 40,
                    suppressScrollX: true
                });
            });
        },

        //=> Slick carousel
        slickCarousel: function (carousel, itemXl, itemLg, itemMd, itemSm) {
            var $carousel = $(carousel);
            var prev = '<button class="btn-prev btn btn-pill btn-air btn-default btn-icon-only"><i class="la la-angle-left"></i></button>';
            var next = '<button class="btn-next btn btn-pill btn-air btn-default btn-icon-only"><i class="la la-angle-right"></i></button>';

            // Set slick carousel
            $carousel.slick({
                arrows: true,
                dots: false,
                infinite: false,
                slidesToShow: itemXl,
                slidesToScroll: 1,
                speed: 1000,
                prevArrow: prev,
                nextArrow: next,
                autoplay: true,

                // Breakpoints
                responsive: [
                    {
                        breakpoint: 1440,
                        settings: {
                            slidesToShow: itemLg
                        }
                    },

                    {
                        breakpoint: 1200,
                        settings: {
                            slidesToShow: itemMd
                        }
                    },

                    {
                        breakpoint: 640,
                        settings: {
                            slidesToShow: itemSm
                        }
                    },

                    {
                        breakpoint: 380,
                        settings: {
                            slidesToShow: 1,
                            arrows: false
                        }
                    }
                ]
            });
        },

        //=> Initialize app slick carousel
        initSlickCarousel: function () {
            AppConfig.slickCarousel('.carousel-item-4', 4, 3, 2, 1);

            AppConfig.slickCarousel('.carousel-item-5', 5, 4, 3, 2);

            AppConfig.slickCarousel('.carousel-item-6', 6, 5, 4, 2);
        },

        //=> App basic buttons click events
        buttonClickEvents: function () {
            $('#toggleSidebar').on('click', function () {
                $body.toggleClass('iconic-sidebar');
            });

            $('#openSidebar').on('click', function (e) {
                e.stopPropagation();
                $body.removeClass('open-search');
                $body.addClass('open-sidebar');
                $sidebarBackdrop.addClass('show');
                $headerBackdrop.removeClass('show');
            });

            $('#hideSidebar').on('click', function () {
                $body.removeClass('open-sidebar');
                $sidebarBackdrop.removeClass('show');
            });

            $('#playList').on('click', function () {
                $body.toggleClass('open-right-sidebar');
            });
        },

        //=> Config search ui events
        search: function () {
            var $search = $('#searchForm #searchInput');

            $search.on('click', function (e) {
                e.stopPropagation();
                $body.addClass('open-search');
                $headerBackdrop.addClass('show');
            });

            $body.on('click', function () {
                $body.removeClass('open-search');
                $headerBackdrop.removeClass('show');
            });
        },

        //=> Material tabs
        materialTab: function () {
            var lineClassName = 'tabs-link-line';
            var $activeLink = $('.line-tabs .nav-item .active');
            var $lineTabsItem = $('.line-tabs .nav-item');

            var line = "<span class='" + lineClassName + "'></span>";
            $('.line-tabs').append(line);

            var activePos = $activeLink.position(),
                activeWidth = $activeLink.parent().width();
            $('.' + lineClassName).stop().css({
                width: activeWidth
            });

            $lineTabsItem.on("click", function() {
                activePos = $(this).position();
                activeWidth = $(this).width();
                $(this).parent().parent().find('.' + lineClassName).stop().css({
                    left: activePos.left,
                    width: activeWidth
                });
            });
        },

        //=> Initialize countdown
        initCountdown: function () {
            var $countdown = $(".countdown");
            var DATE = new Date();
            DATE.setDate(DATE.getDate() + 5);

            $countdown.countdown(DATE, function (event) {
                $(this).html(
                    event.strftime(
                        '<div class="timer-wrapper">' +
                        '<div class="timer-data">%D</div>' +
                        '</div>' +
                        '<span class="time-separate">:</span>' +
                        '<div class="timer-wrapper">' +
                        '<div class="timer-data">%H</div>' +
                        '</div>' +
                        '<span class="time-separate">:</span>' +
                        '<div class="timer-wrapper">' +
                        '<div class="timer-data">%M</div>' +
                        '</div>' +
                        '<span class="time-separate">:</span>' +
                        '<div class="timer-wrapper">' +
                        '<div class="timer-data">%S</div>' +
                        '</div>'
                    )
                );
            });
        },

        //=> Add favorite
        /*addFavorite: function () {
            var $favorite = $('.favorite');
            var heart = '<li><span class="badge badge-pill badge-danger"><i class="la la-heart"></i></span></li>';

            $favorite.on('click', function (e) {
                e.stopPropagation();
                var $this = $(this);
                var info = $this.closest('.custom-card--info');
                var labels = info.find('.custom-card--labels');

                if (labels.length && !info.find('.custom-card--labels li .la-heart').length) {
                    labels.append(heart);
                } else {
                    var $labels = '<ul class="custom-card--labels d-flex">' +
                        heart +
                        '</ul>';
                    info.prepend($labels);
                }
            })
        }*/
    };

    var $body = $('body'),
        $headerBackdrop = $('.header-backdrop'),
        $sidebarBackdrop = $('.sidebar-backdrop');

    //=> Call class at document ready
    $(document).ready(AppConfig.init);
});

//=> Loader
$(window).on('load', function () {
    $('#loading').fadeOut(1000);
});

$('#wrapper').on("scroll", function() {
    $('#header').toggleClass('scrolled', $(this).scrollTop() > 80);
});

"use strict";


/*
//=> Class Definition
var AudioPlayer = AudioPlayer || {};

$(function () {
    AudioPlayer = {
        //=> Initialize function to call all functions of the class
        init: function () {
            if ($('#audioPlayer').length === 0) {
                return false;
            }
            AudioPlayer.initAudioPlayer();
            AudioPlayer.volumedropdownClick();
            AudioPlayer.volumeIconClick();
            AudioPlayer.addAudioInPlayer();
        },

        //=> Initialize audio player
        initAudioPlayer: function () {
            Amplitude.init({
                "songs": [
                    {
                        "name": "I Love You Mummy",
                        "artist": "Arebica Luna",
                        "album": "Mummy",
                        "url": "../assets/audio/ringtone-1.mp3",
                        "cover_art_url": "../assets/images/cover/small/1.jpg"
                    }
                ],
                "playlists": {
                    "special": {
                        songs: [
                            {
                                "name": "I Love You Mummy",
                                "artist": "Arebica Luna",
                                "album": "Mummy",
                                "url": "../assets/audio/ringtone-1.mp3",
                                "cover_art_url": "../assets/images/cover/small/1.jpg"
                            },
                            {
                                "name": "Shack Your Butty",
                                "artist": "Gerrina Linda",
                                "album": "Hot Shot",
                                "url": "../assets/audio/ringtone-2.mp3",
                                "cover_art_url": "../assets/images/cover/small/2.jpg"
                            },
                            {
                                "name": "Do It Your Way(Female)",
                                "artist": "Zunira Willy & Nutty Nina",
                                "album": "Own Way",
                                "url": "../assets/audio/ringtone-3.mp3",
                                "cover_art_url": "../assets/images/cover/small/3.jpg"
                            },
                            {
                                "name": "Say Yes",
                                "artist": "Johnny Marro",
                                "album": "Say Yes",
                                "url": "../assets/audio/ringtone-4.mp3",
                                "cover_art_url": "../assets/images/cover/small/4.jpg"
                            },
                            {
                                "name": "Where Is Your Letter",
                                "artist": "Jina Moore & Lenisa Gory",
                                "album": "Letter",
                                "url": "../assets/audio/ringtone-5.mp3",
                                "cover_art_url": "../assets/images/cover/small/5.jpg"
                            },
                            {
                                "name": "Hey Not Me",
                                "artist": "Rasomi Pelina",
                                "album": "Find Soul",
                                "url": "../assets/audio/ringtone-6.mp3",
                                "cover_art_url": "../assets/images/cover/small/6.jpg"
                            },
                            {
                                "name": "Deep Inside",
                                "artist": "Pimila Holliwy",
                                "album": "Deep Inside",
                                "url": "../assets/audio/ringtone-7.mp3",
                                "cover_art_url": "../assets/images/cover/small/7.jpg"
                            }
                        ]
                    }
                }
            });
        },

        //=> Volume dropdown click
        volumedropdownClick: function () {
            $(document).on('click', '.volume-dropdown-menu', function (e) {
                e.stopPropagation();
            });
        },

        //=> Change volume icon in audio player from it's range
        volumeIconClick: function () {
            var $audioInput = $('.audio-volume input[type="range"]');
            var $audioButton = $('.audio-volume .btn');

            $audioInput.on('change', function () {
                var $this = $(this);
                var value = parseInt($this.val(), 10);

                if (value === 0) {
                    $audioButton.html('<i class="ion-md-volume-mute"></i>');
                } else if (value > 0 && value < 70) {
                    $audioButton.html('<i class="ion-md-volume-low"></i>');
                } else if (value > 70) {
                    $audioButton.html('<i class="ion-md-volume-high"></i>');
                }
            })
        },

        //=> Add audio in player on click of card
        addAudioInPlayer: function () {
            var $audio = $('a[data-audio]');

            $audio.on('click', function () {
                var audioData = $(this).data('audio');
                Amplitude.removeSong(0);
                Amplitude.playNow(audioData);
            })
        }
    };

    //=> Call class at document ready
    $(document).ready(AudioPlayer.init);
});
*/



/**
 * Theme Settings v1.0.0
 * Copyright 2019 Kri8thm
 * Licensed under MIT
 *------------------------------------*/
/*

;(function ($, window, document, undefined) {

    function Theme(element, options) {

        this.$body = $('body');

        /!*
         * Theme settings options
         *!/
        this.options = $.extend({}, Theme.Defaults, options);

        /!*
         * Options to store in cookies
         *!/
        this.cookiesOptions = {
            'themeDark': this.options.darkTheme,
            'header': this.options.header,
            'sidebar': this.options.sidebar,
            'player': this.options.player
        };

        /!*
         * Get cookies of app and set on options
         *!/
        if ($.cookie('themeSetting') != null && options === false) {
            this.cookiesOptions = JSON.parse($.cookie('themeSetting'));
            this.options.darkTheme = this.cookiesOptions.themeDark;
            this.options.header = this.cookiesOptions.header;
            this.options.sidebar = this.cookiesOptions.sidebar;
            this.options.player = this.cookiesOptions.player;
        }

        /!*
         * Count for checkbox
         *!/
        this.optionList = [
            {
                'text': 'Dark Theme',
                'value': this.options.darkTheme
            }
        ];

        var pageName = window.location.pathname.split('/').pop().split('.')[0];
        var pages = ['index', 'error'];
        var isSettingNotVisible = pages.includes(pageName);
        if (pageName && !isSettingNotVisible) {
            this.initialize();
        }
    }

    /!**
     * Default options for the theme.
     * @public
     *!/
    Theme.Defaults = {
        darkTheme: false,

        header: 0,
        sidebar: 0,
        player: 0,
        themeClass: ['primary', 'danger', 'success', 'warning', 'info', 'brand', 'dark'],

        settingsButton: 'button',
        settingsButtonId: 'customSettings',
        settingsButtonClass: 'btn btn-pill btn-air btn-brand btn-icon-only',
        settingIcon: '<i class="ion-md-settings"></i>',

        itemElement: 'div',
        wrapperId: 'settingsWrapper',

        listClass: 'custom-list',
        listItemClass: 'custom-list--item'
    };

    /!**
     * Initializes the theme settings.
     * @protected
     *!/
    Theme.prototype.initialize = function () {
        var $header = $('#header');
        var $sidebar = $('#sidebar');
        var $player = $('#audioPlayer');

        if (this.options.darkTheme) {
            this.$body.addClass('theme-dark');
        }
        $header.addClass('bg-' + this.options.themeClass[this.options.header]);
        $sidebar.addClass('sidebar-' + this.options.themeClass[this.options.sidebar]);
        $player.addClass('player-' + this.options.themeClass[this.options.player]);
        this.settingsButtonElement();
        this.skinClicks();
    };

    /!**
     * Add theme settings button.
     * @protected
     *!/
    Theme.prototype.settingsButtonElement = function () {
        var attributes = {
            'type': 'button',
            'id': this.options.settingsButtonId,
            'className': this.options.settingsButtonClass
        };

        var btnElement = document.createElement(this.options.settingsButton);
        Object.assign(btnElement, attributes);
        btnElement.innerHTML = this.options.settingIcon;
        this.$body.append(btnElement);
        this.themeOptions();
    };

    /!**
     * Add theme settings options.
     * @protected
     *!/
    Theme.prototype.themeOptions = function () {
        var wrapperElement = document.createElement(this.options.itemElement);
        wrapperElement.setAttribute('id', this.options.wrapperId);

        var header = '<header>' +
            '<span class="title-bold font-md text-uppercase">Theme Settings</span>' +
            '<a href="javascript:void(0)" class="ml-auto"><i class="ion-md-close"></i></a>' +
            '</header>';

        var body = '<div class="theme-settings-body"><ul class="' + this.options.listClass + '">';

        for (var i = 0; i < this.optionList.length; i++) {
            var checked = this.optionList[i].value ? 'checked' : '';

            body += '<li class="' + this.options.listItemClass + '">' +
                '<label for="to' + i + '">' + this.optionList[i].text + '</label>' +
                '<div class="custom-control custom-checkbox ml-auto">' +
                '<input type="checkbox" class="custom-control-input" id="to' + i + '" ' + checked + '>' +
                '<label class="custom-control-label" for="to' + i + '"></label>' +
                '</div>' +
                '</li>';
        }

        body += '<li class="custom-list-group--item-separator"></li>' +
            '<li class="custom-list-group--item custom-list-group--item-header">Header Colors</li>' +
            '<li class="' + this.options.listItemClass + '">';

        for (var j = 0; j < this.options.themeClass.length; j++) {
            var activeClass = j === this.options.header ? 'active' : '';
            body += '<a href="javascript:void(0);" class="header-skin bg-' + this.options.themeClass[j] + ' ' + activeClass + '" ' +
                'data-header-skin="' + j + '"></a>';
        }

        body += '</li>';

        body += '<li class="custom-list-group--item-separator"></li>' +
            '<li class="custom-list-group--item custom-list-group--item-header">Sidebar Colors</li>' +
            '<li class="' + this.options.listItemClass + '">';

        for (var k = 0; k < this.options.themeClass.length; k++) {
            var activeClassSidebar = k === this.options.sidebar ? 'active' : '';
            body += '<a href="javascript:void(0);" class="sidebar-skin bg-' + this.options.themeClass[k] + ' ' + activeClassSidebar + '" ' +
                'data-sidebar-skin="' + k + '"></a>';
        }

        body += '</li>';

        body += '<li class="custom-list-group--item-separator"></li>' +
            '<li class="custom-list-group--item custom-list-group--item-header">Player Colors</li>' +
            '<li class="' + this.options.listItemClass + '">';

        for (var m = 0; m < this.options.themeClass.length; m++) {
            var activeClassPlayer = m === this.options.player ? 'active' : '';
            body += '<a href="javascript:void(0);" class="player-skin bg-' + this.options.themeClass[m] + ' ' + activeClassPlayer + '" ' +
                'data-player-skin="' + m + '"></a>';
        }

        body += '</li>';

        body += '</ul></div>';

        wrapperElement.innerHTML = header + body;
        this.$body.append(wrapperElement);
    };
*/
/*

    /!**
     * App click events.
     * @protected
     *!/
    Theme.prototype.skinClicks = function () {
        var _this = this;
        var settings = '#' + _this.options.settingsButtonId;
        var $wrapper = $('#' + _this.options.wrapperId);
        var $header = $('#header');
        var $sidebar = $('#sidebar');
        var $player = $('#audioPlayer');
        var $headerSkin = $('.header-skin');
        var $sidebarSkin = $('.sidebar-skin');
        var $playerSkin = $('.player-skin');

        this.$body.on('click', '#to0', function () {
            var $this = $(this);
            _this.cookiesOptions.themeDark = $this[0].checked;
            _this.$body.toggleClass('theme-dark');
            _this.setCookies();
        });

        this.$body.on('click', '.header-skin', function () {
            var $this = $(this);
            var headerSkin = $this.data('header-skin');
            _this.cookiesOptions.header = headerSkin;
            $header.removeClass();
            $header.addClass('bg-' + _this.options.themeClass[headerSkin]);
            $headerSkin.removeClass('active');
            $this.addClass('active');
            _this.setCookies();
        });

        this.$body.on('click', '.sidebar-skin', function () {
            var $this = $(this);
            var sidebarSkin = $this.data('sidebar-skin');
            _this.cookiesOptions.sidebar = sidebarSkin;
            $sidebar.removeClass();
            $sidebar.addClass('sidebar-' + _this.options.themeClass[sidebarSkin]);
            $sidebarSkin.removeClass('active');
            $this.addClass('active');
            _this.setCookies();
        });

        this.$body.on('click', '.player-skin', function () {
            var $this = $(this);
            var playerSkin = $this.data('player-skin');
            _this.cookiesOptions.player = playerSkin;
            $player.removeClass();
            $player.addClass('player-' + _this.options.themeClass[playerSkin]);
            $playerSkin.removeClass('active');
            $this.addClass('active');
            _this.setCookies();
        });

        this.$body.on('click', settings, function () {
            $wrapper.toggleClass('open-settings');
        });

        this.$body.on('click', 'header a', function () {
            $wrapper.removeClass('open-settings');
        });
    };

    /!**
     * Set app cookies.
     * @protected
     *!/
    Theme.prototype.setCookies = function () {
        $.cookie('themeSetting', JSON.stringify(this.cookiesOptions), {expires: 7, path: '/'});
    };

    /!**
     * The jQuery Plugin for the Theme Setting
     * @public
     *!/
    $.fn.themeSettings = function (option) {
        return this.each(function () {
            var $this = $(this);
            var data = new Theme(this, typeof option === 'object' && option);
        });
    };

    /!**
     * The constructor for the jQuery Plugin
     * @public
     *!/
    $.fn.themeSettings.Constructor = Theme;

})(jQuery, window, document);*/
