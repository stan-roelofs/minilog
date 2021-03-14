package nl.stanroelofs.minilog

import nl.stanroelofs.minilog.loggerfactory.DefaultLoggerFactory
import nl.stanroelofs.minilog.loggerfactory.LoggerFactory

object Logging : LoggerFactory by DefaultLoggerFactory()