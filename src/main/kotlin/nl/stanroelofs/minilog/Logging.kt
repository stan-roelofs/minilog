package nl.stanroelofs.minilog

import nl.stanroelofs.minilog.loggerfactory.ILoggerFactory
import nl.stanroelofs.minilog.loggerfactory.LoggerFactory

object Logging : ILoggerFactory by LoggerFactory()